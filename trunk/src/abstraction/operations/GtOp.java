package abstraction.operations;

import intervalAnalysis.State;
import abstraction.Bottom;
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
			return this.bottom.clone();
		}
	}


	public State op(State in, Local x, IntConstant a)
	{
		LatticeElement aInterval = new PositiveInf(a.value+1); 
		LatticeElement xInterval = in.getVarState(x);
		LatticeElement meetResult = xInterval.meet(aInterval);
		
		if (meetResult.equals(new Bottom()))
		{
			return this.bottom.clone();
		}
		
		State out = in.clone();
		out.setVarState(x, meetResult);
		
		return out;	
		
	}


	public State op(State in, IntConstant a, Local x){
		LatticeElement aInterval = new NegativeInf(a.value-1); 
		LatticeElement xInterval = in.getVarState(x);
		LatticeElement meetResult = xInterval.meet(aInterval);
		
		if (meetResult.equals(new Bottom()))
		{
			return bottom.clone();
			
		}
		
		State out = in.clone();
		out.setVarState(x, meetResult);
		
		return out;	
	}
	

	public State op(State in, Local x, Local y){
		
		if (x.equals(y))
		{	// x not greater than x
			return bottom.clone();
		}
		
		LatticeElement xInterval = in.getVarState(x);
		LatticeElement yInterval = in.getVarState(y);

		LatticeElement checkXInterval = yInterval.createLowToPositiveInf();
		checkXInterval = checkXInterval.add(new Interval(1,1));
		
		//check if the there is a true path
		LatticeElement xMeet = xInterval.meet(checkXInterval);
		
		if (xMeet.equals(new Bottom()))
		{	//the condition doesn't hold
			return bottom.clone();
		}
		
		LatticeElement checkYInterval = xInterval.createNegativeInfToHigh();
		checkYInterval = checkYInterval.sub(new Interval(1,1));

		LatticeElement yMeet = yInterval.meet(checkYInterval);
		
		State out = in.clone();
		out.setVarState((Value)x, xMeet);
		out.setVarState((Value)y, yMeet);
		return out;

	}

	@Override
	public State negate(State in, Value left, Value right) {
		return new LeOp().op(in, left, right);
	}




}
