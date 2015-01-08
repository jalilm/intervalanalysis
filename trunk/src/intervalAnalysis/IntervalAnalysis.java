package intervalAnalysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import abstraction.Interval;
import abstraction.LatticeElement;
import abstraction.Top;
import soot.Unit;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.IntConstant;
import soot.jimple.NumericConstant;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardBranchedFlowAnalysis;
import tools.StatementVisitor;

public class IntervalAnalysis extends ForwardBranchedFlowAnalysis<State> {

    State initState;
    Map<Unit, Integer> unitToCounter;

    public IntervalAnalysis(UnitGraph graph) {
        super(graph);
        initState = new State();
        unitToCounter = new HashMap<Unit, Integer>();
        for (Iterator<Unit> unitIt = graph.iterator(); unitIt.hasNext();) {
            Unit s = (Unit) unitIt.next();
            unitToCounter.put(s, 0);
            if (s.getClass().getName()
                    .equals("soot.jimple.internal.JIdentityStmt")) {
                Value v = s.getDefBoxes().get(0).getValue();
                initState.setVarState(v, new Top());
            } else if (s.getClass().getName()
                    .equals("soot.jimple.internal.JAssignStmt")) {
                LatticeElement rightOpState = null;
                AssignStmt AStmt = (AssignStmt) s;
                Value leftOp = AStmt.getLeftOp();
                Value rightOp = AStmt.getRightOp();
                if (rightOp instanceof NumericConstant) {
                    rightOpState = new Interval((IntConstant) rightOp,
                            (IntConstant) rightOp);
                    initState.setVarState(leftOp, rightOpState);
                }
            }
        }

        doAnalysis();
        System.out.println(initState.print());
    }

    @Override
    protected void flowThrough(State inState, Unit stmt, List<State> fallOut,
            List<State> BranchOut) {
        unitToCounter.put(stmt, unitToCounter.get(stmt)+1);
        if (unitToCounter.get(stmt)== 10) {
            widen(inState, stmt, fallOut, BranchOut);
            return;
        }
        StatementVisitor visitor = new StatementVisitor();
        visitor.visit(stmt, inState, fallOut, BranchOut);
    }

    private void widen(State inState, Unit stmt, List<State> fallOut,
            List<State> BranchOut) {
        unitToCounter.put(stmt,0);
    }

    @Override
    protected void copy(State src, State dest) {
        src.copy(dest);
    }

    @Override
    protected State entryInitialFlow() {
        return initState;
    }

    @Override
    protected void merge(State in1, State in2, State out) {
        out = in1.merge(in2);
    }

    @Override
    protected State newInitialFlow() {
        return initState;
    }

}
