package intervalAnalysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import abstraction.LatticeElement;
import abstraction.Top;
import soot.Unit;
import soot.Value;
import soot.jimple.internal.JRetStmt;
import soot.jimple.internal.JReturnStmt;
import soot.jimple.internal.JReturnVoidStmt;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardBranchedFlowAnalysis;
import tools.StatementVisitor;

public class IntervalAnalysis extends ForwardBranchedFlowAnalysis<State> {

    final int wideningThreshold = 10;
    final State resultState; // will hold the result
    final State initState = new State(); // holds initial State of arguments ->
                                         // [-inf,inf]
    Map<Unit, Integer> unitToCounter;
    Map<Value, LatticeElement> varToElement;

    public IntervalAnalysis(UnitGraph graph, State state) {
        super(graph);
        resultState = state;
        if (graph.getBody().getMethod().getName().equals("<init>")) {
            return;
        }
        unitToCounter = new HashMap<Unit, Integer>();
        varToElement = new HashMap<Value, LatticeElement>();
        // Add parameters with [-inf,inf]
        for (Iterator<Unit> unitIt = graph.iterator(); unitIt.hasNext();) {
            Unit s = (Unit) unitIt.next();
            unitToCounter.put(s, 0);
            if (s.getClass().getName()
                    .equals("soot.jimple.internal.JIdentityStmt")) {
                Value v = s.getDefBoxes().get(0).getValue();
                initState.setVarState(v, new Top());
            }
        }

        doAnalysis();
        System.err.println("IntervalAnalysis - The result is: ");
        System.err.println(resultState.print());
    }

    @Override
    protected void flowThrough(State inState, Unit stmt, List<State> fallOut,
            List<State> BranchOut) {
        Value var = null;
            if (unitToCounter.get(stmt) > wideningThreshold) {
                for (State s : fallOut) {
                    inState.copy(s);
                }

                for (State s : BranchOut) {
                    inState.copy(s);
                }
                System.err.println("*******************************");
                System.err.println("***     Already Widened  ******");
                System.err.println("*******************************");
                System.err.println("COMMAND  : " + stmt.toString());
                System.err.println("TYPE     : " + stmt.getClass().getName());
                System.err.println("IN STATE : " + inState.toString());
                for (State s : fallOut) {
                    System.err.println("FALLOUT  : " + s.toString());
                }
                for (State s : BranchOut) {
                    System.err.println("BRANCHOUT : " + s.toString());
                }
                return;
            } else if (unitToCounter.get(stmt) == wideningThreshold) {
                if(stmt.getDefBoxes().size() != 0) {
                    var = stmt.getDefBoxes().get(0).getValue();
                    LatticeElement lastElement = varToElement.get(var);
                    LatticeElement currElement = inState.getVarState(var);
                    LatticeElement widenElement = lastElement.widen(currElement);
                    if (!currElement.equals(widenElement)) {
                        for (State s : fallOut) {
                            State out = inState.clone();
                            out.setVarState(var, widenElement);
                            out.copy(s);
                        }
                        for (State s : BranchOut) {
                            State out = inState.clone();
                            out.setVarState(var, widenElement);
                            out.copy(s);
                        }
                        System.err.println("*******************************");
                        System.err.println("***     Widening         ******");
                        System.err.println("*******************************");
                        System.err.println("COMMAND  : " + stmt.toString());
                        System.err.println("TYPE     : "
                                + stmt.getClass().getName());
                        System.err.println("PREV LAT : " + lastElement.toString());
                        System.err.println("IN STATE : " + inState.toString());
                        for (State s : fallOut) {
                            System.err.println("FALLOUT  : " + s.toString());
                        }
                        unitToCounter.put(stmt, unitToCounter.get(stmt) + 1);
                        return;
                    }
                } else {
                    unitToCounter.put(stmt, unitToCounter.get(stmt) + 1);
                }
            } else {
                unitToCounter.put(stmt, unitToCounter.get(stmt) + 1);
                if(stmt.getDefBoxes().size() != 0) {
                    var = stmt.getDefBoxes().get(0).getValue();
                    varToElement.put(var, inState.getVarState(var));
                }
            }

        System.err.println("*******************************");
        System.err.println("***     FLOWTHROUGH      ******");
        System.err.println("*******************************");
        System.err.println("COMMAND  : " + stmt.toString());
        System.err.println("TYPE     : " + stmt.getClass().getName());
        System.err.println("IN STATE : " + inState.toString());

        if ((stmt instanceof JReturnStmt || stmt instanceof JReturnVoidStmt || stmt instanceof JRetStmt)) {
            // join to results from other methods
            inState.copy(resultState);
        }

        StatementVisitor visitor = new StatementVisitor();
        visitor.visit(stmt, inState, fallOut, BranchOut);

        for (State s : fallOut) {
            System.err.println("FALLOUT  : " + s.toString());
        }
        for (State s : BranchOut) {
            System.err.println("BRANCHOUT : " + s.toString());
        }
    }

    @Override
    protected void copy(State src, State dest) {
        src.copy(dest);
    }

    @Override
    protected State entryInitialFlow() {
        System.err.println("*******************************");
        System.err.println("***     ENTRY            ******");
        System.err.println("*******************************");
        System.err.println("ENTRY : " + initState.toString());
        return initState.clone();
    }

    @Override
    protected void merge(State in1, State in2, State out) {
        System.err.println("*******************************");
        System.err.println("***     MERGE            ******");
        System.err.println("*******************************");
        System.err.println("STATE1 : " + in1.toString());
        System.err.println("STATE2 : " + in2.toString());
        State result = in1.join(in2);
        result.copy(out);
        System.err.println("OUT : " + out.toString());
    }

    @Override
    protected State newInitialFlow() {
        System.err.println("*******************************");
        System.err.println("***     NEW            ******");
        System.err.println("*******************************");
        System.err.println("NEW : " + initState.toString());
        return initState.clone();

    }

}
