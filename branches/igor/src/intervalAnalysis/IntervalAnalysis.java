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
import soot.jimple.internal.JReturnStmt;
import soot.jimple.internal.JReturnVoidStmt;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardBranchedFlowAnalysis;
import tools.StatementVisitor;

public class IntervalAnalysis extends ForwardBranchedFlowAnalysis<State> {

    final int wideningThreshold = 10;
    final State resultState; //will hold the result
    final State initState = new State(); //holds initial State of arguments -> [-inf,inf]
    Map<Unit, Integer> unitToCounter;
    Map<Value, LatticeElement> varToElement;

    public IntervalAnalysis(UnitGraph graph, State state) {
        super(graph);
        resultState = state;
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
        System.out.println("IntervalAnalysis: Hello again " + initState.print());
    }

    @Override
    protected void flowThrough(State inState, Unit stmt, List<State> fallOut,
            List<State> BranchOut) {
    	System.out.println("*******************************");
    	System.out.println("***     FLOWTHROUGH      ******");
    	System.out.println("*******************************");
    	System.out.println("COMMAND  : " + stmt.toString());
    	System.out.println("TYPE     : " + stmt.getClass().getName());
    	System.out.println("IN STATE : " + inState.toString());
    	
    	if ((stmt instanceof JReturnStmt || stmt instanceof JReturnVoidStmt))
    	{
    		//join to results from other methods
    		State joined = inState.join(resultState);
    		joined.copy(resultState);
    	}
    	
        StatementVisitor visitor = new StatementVisitor();
        visitor.visit(stmt, inState, fallOut, BranchOut);
        
        for (State s : fallOut)
        {
        	System.out.println("FALLOUT  : " + s.toString());
        }
        for (State s : BranchOut)
        {
        	System.out.println("BRANCHOUT : " + s.toString());
        }
    }

    @Override
    protected void copy(State src, State dest) {
        src.copy(dest);
    }

    @Override
    protected State entryInitialFlow() {
    	System.out.println("*******************************");
    	System.out.println("***     ENTRY            ******");
    	System.out.println("*******************************");
    	System.out.println("ENTRY : " + initState.toString());
        return initState.clone();
    }

    @Override
    protected void merge(State in1, State in2, State out) {
    	System.out.println("*******************************");
    	System.out.println("***     MERGE            ******");
    	System.out.println("*******************************");
    	System.out.println("STATE1 : " + in1.toString());
    	System.out.println("STATE2 : " + in2.toString());
        State result = in1.join(in2);
        result.copy(out);
        System.out.println("OUT : " + out.toString());
    }

    @Override
    protected State newInitialFlow() {
    	System.out.println("*******************************");
    	System.out.println("***     NEW            ******");
    	System.out.println("*******************************");
    	System.out.println("NEW : " + initState.toString());
    	return initState.clone();
        
    }

}
