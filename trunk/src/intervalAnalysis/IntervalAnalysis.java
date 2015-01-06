package intervalAnalysis;

import java.util.List;

import soot.Unit;
import soot.UnitBox;
import soot.ValueBox;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardBranchedFlowAnalysis;
import tools.StatementVisitor;

public class IntervalAnalysis extends ForwardBranchedFlowAnalysis<State> {

    public IntervalAnalysis(UnitGraph graph) {
        super(graph);
        doAnalysis();
    }

    @Override
    protected void flowThrough(State inState, Unit stmt, List<State> fallOut,
            List<State> BranchOut) {
        StatementVisitor visitor = new StatementVisitor();
        visitor.visit(stmt,inState,fallOut,BranchOut);
        /*
        List<UnitBox> units;
        List<ValueBox> defs, uses, usesAndDefs;
        //TODO Switch
        if (stmt.branches()) {
            if (stmt.fallsThrough()) {
                System.out.println("If");
            } else {
                System.out.println("Goto");
            }
        }
        units = stmt.getUnitBoxes();
        defs = stmt.getDefBoxes();
        for (ValueBox e : defs) {
            e.getValue();
        }
        uses = stmt.getUseBoxes();
        usesAndDefs = stmt.getUseAndDefBoxes();
        return;
        */
    }

    @Override
    protected void copy(State src, State dest) {
        src.copy(dest);
    }

    @Override
    protected State entryInitialFlow() {
        return new State();
    }

    @Override
    protected void merge(State in1, State in2, State out) {
        out = in1.merge(in2);
    }

    @Override
    protected State newInitialFlow() {
        return new State();
    }

}
