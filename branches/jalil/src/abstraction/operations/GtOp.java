package abstraction.operations;

import intervalAnalysis.State;



import abstraction.LatticeElement;
import abstraction.PositiveInf;
import soot.Local;
import soot.Value;
import soot.jimple.IntConstant;



public class GtOp extends AbstractLogicOperation{

	@Override
	public State op(State in, IntConstant left, IntConstant right) {
		if (left.value > right.value)
		{
			return in;
		}
		else
		{
			//TODO return bottom
			return new State();
		}
	}

	@Override
	public State op(State in, Local left, IntConstant right) 
	{
		
		LatticeElement leftInt = in.getLatticeElement(left);
		IntConstant upper = IntConstant.v(right.value+1);
		LatticeElement rightInt = new PositiveInf(upper); 
		LatticeElement meet = rightInt.meet(leftInt); 
		in.updateLatticeElement(left,meet);
		return in;

	}

	@Override
	public State op(State in, IntConstant left, Local right) {
		AbstractLogicOperation o = new LtOp();
		return o.op(in, right, left);
	}
	
	@Override
	public State op(State in, Local left, Local right) {
		
		LatticeElement leftInt = in.getLatticeElement(left);
		LatticeElement rightInt = in.getLatticeElement(right);
		if (leftInt == null || rightInt == null)
		{
			return in;
		}
		//TODO
		LatticeElement meet = rightInt.meet(leftInt); 
		in.updateLatticeElement(left,meet);
		return in;

	}

	@Override
	public State negate(State in, Value left, Value right) {
		return new GeOp().op(in, right, left);
	}




}
