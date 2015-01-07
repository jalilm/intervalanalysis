package intervalAnalysis;

import transform.IJoinMeetTransformer;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public abstract class AbstractInterval implements LatticeElement, IJoinMeetTransformer  {

	@Override
	public void joinInterval(Interval other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinTop(Top other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinBottom(Bottom other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinPositiveInf(PositiveInf other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinNegativeInf(NegativeInf other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void meetInterval(Interval other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void meetTop(Top other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void meetBottom(Bottom other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void meetPositiveInf(PositiveInf other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void meetNegativeInf(NegativeInf other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LatticeElement add(IMathTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement sub(IMathTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement mul(IMathTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement div(IMathTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement mod(IMathTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement eq(ILogicalTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement neq(ILogicalTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement gt(ILogicalTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement ge(ILogicalTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement lt(ILogicalTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement le(ILogicalTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement join(IJoinMeetTransformer varState) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LatticeElement meet(IJoinMeetTransformer varState) {
		// TODO Auto-generated method stub
		return null;
	}

}
