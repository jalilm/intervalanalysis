package abstraction.operations;

import intervalAnalysis.State;


import soot.Value;


public class LtOp implements ILogicOperation {

	@Override
	public State op(State in, Value left, Value right) {
		return new GtOp().op(in, right, left);
	}

	@Override
	public State negate(State in, Value left, Value right) {
		return new GeOp().op(in, left, right);
	}

}
