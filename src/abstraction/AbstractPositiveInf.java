package abstraction;

import soot.jimple.IntConstant;
import transform.IJoinMeetTransformer;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public abstract class AbstractPositiveInf implements LatticeElement {

    @Override
    public LatticeElement add(IMathTransformer transformer) {
        return transformer.addPositiveInf((PositiveInf) this);
    }

    @Override
    public LatticeElement sub(IMathTransformer transformer) {
        return transformer.subPositiveInf((PositiveInf) this);
    }

    @Override
    public LatticeElement mul(IMathTransformer transformer) {
        return transformer.mulPositiveInf((PositiveInf) this);
    }

    @Override
    public LatticeElement div(IMathTransformer transformer) {
        return transformer.divPositiveInf((PositiveInf) this);
    }

    @Override
    public LatticeElement mod(IMathTransformer transformer) {
        return transformer.modPositiveInf((PositiveInf) this);
    }

    @Override
    public LatticeElement join(IJoinMeetTransformer transformer) {
        return transformer.joinPositiveInf((PositiveInf) this);
    }

    @Override
    public LatticeElement meet(IJoinMeetTransformer transformer) {
        return transformer.meetPositiveInf((PositiveInf) this);
    }

    @Override
    public LatticeElement widen(IJoinMeetTransformer transformer) {
        return transformer.widenPositiveInf((PositiveInf) this);
    }

    @Override
    public LatticeElement joinInterval(Interval other) {
        return new PositiveInf(IntConstant.v(Math.min(
                ((PositiveInf) this).low.value, other.low.value)));
    }

    @Override
    public LatticeElement joinTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement joinBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement joinPositiveInf(PositiveInf other) {
        return new PositiveInf(IntConstant.v(Math.min(
                ((PositiveInf) this).low.value, other.low.value)));
    }

    @Override
    public LatticeElement joinNegativeInf(NegativeInf other) {
        return new Top();
    }

    @Override
    public LatticeElement widenInterval(Interval other) {
        PositiveInf thisPos = ((PositiveInf) this);
        if (thisPos.low.value < other.low.value) {
            return new Top();
        } else {
            return new PositiveInf(other.low);
        }
    }

    @Override
    public LatticeElement widenTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement widenBottom(Bottom other) {
        return new Top();
    }

    @Override
    public LatticeElement widenPositiveInf(PositiveInf other) {
        PositiveInf thisPos = (PositiveInf) this;
        if (thisPos.low.value < other.low.value) {
            return new Top();
        } else {
            return other;
        }
    }

    @Override
    public LatticeElement widenNegativeInf(NegativeInf other) {
        return new Top();
    }

    @Override
    public LatticeElement meetInterval(Interval other) {
        return other.meetPositiveInf((PositiveInf)this);
    }

    @Override
    public LatticeElement meetTop(Top other) {
        return this;
    }

    @Override
    public LatticeElement meetBottom(Bottom other) {
        return other;
    }

    @Override
    public LatticeElement meetPositiveInf(PositiveInf other) {
    	int a = Math.max(((PositiveInf) this).low.value, other.low.value); 
    	return new PositiveInf(a);

    }

    @Override
    public LatticeElement meetNegativeInf(NegativeInf other) {
    	int a = ((PositiveInf) this).low.value; 
    	int b = other.high.value;
    	if (b >= a)
    	{
    		return new Interval(a,b);
    	}
    	else return new Bottom();
    }


}
