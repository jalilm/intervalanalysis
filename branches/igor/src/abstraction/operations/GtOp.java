package abstraction.operations;

import intervalAnalysis.State;
import abstraction.Interval;
import abstraction.LatticeElement;
import abstraction.NegativeInf;
import abstraction.PositiveInf;
import soot.Local;
import soot.Value;
import soot.jimple.IntConstant;



public class GtOp extends AbstractLogicOperation{


	public State op(State in, IntConstant left, IntConstant right) {
		if (left.value > right.value)
		{
			return in.clone();
		}
		else
		{
			//TODO return bottom
			return new State();
		}
	}


	public State op(State in, Local left, IntConstant right)
	{
		LatticeElement newX = new PositiveInf(right.value+1); 
		State out = new State();
		out.setVarState((Value)left, newX);
		return in.meet(out);
	}


	public State op(State in, IntConstant left, Local right){
		LatticeElement newX = new NegativeInf(left.value-1); 
		State out = new State();
		out.setVarState((Value)right, newX);
		return in.meet(out);
	}
	

	public State op(State in, Local left, Local right){
		
		LatticeElement x = in.getVarState(left);
		LatticeElement y = in.getVarState(right);

		LatticeElement newX = y.createLowToPositiveInf();
		newX = newX.add(new Interval(1,1));
		LatticeElement newY = x.createNegativeInfToHigh();
		newY = newY.sub(new Interval(1,1));

		State out = new State();
		out.setVarState((Value)left, newX);
		out.setVarState((Value)right,newY);
		return in.meet(out);

	}

	@Override
	public State negate(State in, Value left, Value right) {
		return new LeOp().op(in, left, right);
	}




}
