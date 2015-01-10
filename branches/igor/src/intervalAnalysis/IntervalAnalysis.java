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

    final int wideningThreshold = 10;
    State initState;
    Map<Unit, Integer> unitToCounter;
    Map<Value, LatticeElement> varToElement;

    public IntervalAnalysis(UnitGraph graph, State state) {
        super(graph);
        initState = state;
        unitToCounter = new HashMap<Unit, Integer>();
        varToElement = new HashMap<Value, LatticeElement>();
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
    	
        StatementVisitor visitor = new StatementVisitor();
        visitor.visit(stmt, inState, fallOut, BranchOut);
        
        System.out.println(initState.print());
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
        out = in1.join(in2);
    }

    @Override
    protected State newInitialFlow() {
        return initState;
    }

}
