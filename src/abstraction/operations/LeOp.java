package abstraction.operations;

import intervalAnalysis.State;

import soot.Local;
import soot.Value;

import soot.jimple.IntConstant;

public class LeOp extends AbstractLogicOperation {

	@Override
	public State op(State in, IntConstant left, IntConstant right) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State op(State in, Local left, IntConstant right) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State op(State in, IntConstant left, Local right) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State op(State in, Local left, Local right) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State negate(State in, Value left, Value right) {
		// TODO Auto-generated method stub
		return null;
	}

}
