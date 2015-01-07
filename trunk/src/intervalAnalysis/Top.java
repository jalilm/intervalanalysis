package intervalAnalysis;

import transform.IJoinMeetTransformer;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public class Top  extends TopAbstract implements IMathTransformer, ILogicalTransformer {

	@Override
	public void IfEq(LatticeElement other, LatticeElement trueResult,
			LatticeElement falseResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void IfNeq(LatticeElement other, LatticeElement trueResult,
			LatticeElement falseResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void IfGt(LatticeElement other, LatticeElement trueResult,
			LatticeElement falseResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void IfGe(LatticeElement other, LatticeElement trueResult,
			LatticeElement falseResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void IfLt(LatticeElement other, LatticeElement trueResult,
			LatticeElement falseResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void IfLe(LatticeElement other, LatticeElement trueResult,
			LatticeElement falseResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInterval(Interval other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTop(Top other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBottom(Bottom other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPositiveInf(PositiveInf other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNegativeInf(NegativeInf other, LatticeElement result) {
		// TODO Auto-generated method stub
		
	}


}
