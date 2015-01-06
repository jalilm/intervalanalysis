package intervalAnalysis;

import java.util.List;

import soot.Unit;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardBranchedFlowAnalysis;

public class IntervalAnalysis extends ForwardBranchedFlowAnalysis<State> {

    public IntervalAnalysis(UnitGraph graph) {
        super(graph);
        doAnalysis();
    }

    @Override
    protected void flowThrough(State inState, Unit stmt, List<State> fallOut,
            List<State> BranchOut) {
        List units, defs, uses, usesAndDefs;
        System.out.println("Flowing through: " + stmt.toString());
        if (stmt.branches()) {
            if (stmt.fallsThrough()) {
                System.out.println("If");
            } else {
                System.out.println("Goto");
            }
        }
        units = stmt.getUnitBoxes();
        defs = stmt.getDefBoxes();
        uses = stmt.getUseBoxes();
        usesAndDefs = stmt.getUseAndDefBoxes();
        return;
    }

    @Override
    protected void copy(State src, State dest) {
        src.copy(dest);
    }

    @Override
    protected State entryInitialFlow() {
        return new Bottom();
    }

    @Override
    protected void merge(State in1, State in2, State out) {
        // TODO Auto-generated method stub
    }

    @Override
    protected State newInitialFlow() {
        return new Bottom();
    }

}
