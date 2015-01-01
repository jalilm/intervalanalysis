package skeleton;

import java.util.List;

import soot.Unit;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardBranchedFlowAnalysis;

public class Analysis extends ForwardBranchedFlowAnalysis<State> {

	public Analysis(UnitGraph graph) {
		super(graph);
		// TODO Auto-generated constructor stub
		doAnalysis();
	}

	@Override
	protected void flowThrough(State arg0, Unit arg1, List<State> arg2,
			List<State> arg3) {
		// TODO Auto-generated method stub
		System.out.println("Flowing through: " + arg1.toString());
		
	}

	@Override
	protected void copy(State arg0, State arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	protected State entryInitialFlow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void merge(State arg0, State arg1, State arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	protected State newInitialFlow() {
		// TODO Auto-generated method stub
		return null;
	}

}
