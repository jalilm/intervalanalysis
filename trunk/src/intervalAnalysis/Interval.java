package intervalAnalysis;

import soot.jimple.IntConstant;
import transform.IJoinMeetTransformer;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public class Interval extends AbstractInterval implements ILogicalTransformer, IMathTransformer {

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
	/*
    final IntConstant low;
    final IntConstant high;

    public Interval(IntConstant low, IntConstant high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public LatticeElement join(LatticeElement varState) {
        LatticeElement res = null;
        if (varState instanceof Top) {
            res = new Top();
        } else if (varState instanceof Bottom) {
            res = this;
        } else if (varState instanceof Interval) {
            res = new Interval(IntConstant.v(Math.min(this.low.value, ((Interval)varState).low.value)),
                                IntConstant.v(Math.max(this.high.value, ((Interval)varState).high.value)));        
        } else if (varState instanceof PositiveInf) {
            res = new PositiveInf(IntConstant.v(Math.min(this.low.value, ((PositiveInf)varState).low.value)));
        } else if (varState instanceof NegativeInf) {
            res = new PositiveInf(IntConstant.v(Math.max(this.high.value, ((NegativeInf)varState).high.value)));
        } else {
            assert false;
        }
        return res;
    }
*/

}
