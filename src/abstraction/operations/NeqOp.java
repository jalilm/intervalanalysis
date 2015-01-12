package abstraction.operations;

import intervalAnalysis.State;
import soot.Value;


public class NeqOp implements ILogicOperation {

	@Override
	public State op(State in, Value left, Value right) {
		State in1 = in.clone();
		State in2 = in.clone();
		State temp1 = new GtOp().op(in1, left, right);
		State temp2 = new GtOp().op(in2, right, left);
		State temp3 = temp1.join(temp2); 
		return temp3;
	}

	@Override
	public State negate(State in, Value left, Value right) {
		return new EqOp().op(in, left, right);
	}

}
