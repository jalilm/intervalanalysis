package abstraction;

import soot.jimple.IntConstant;
import transform.IJoinMeetTransformer;
import transform.IMathTransformer;

public abstract class AbstractNegativeInf implements LatticeElement {

    @Override
    public LatticeElement add(IMathTransformer transformer) {
        return transformer.addNegativeInf((NegativeInf) this);
    }

    @Override
    public LatticeElement sub(IMathTransformer transformer) {
        return transformer.subNegativeInf((NegativeInf) this);
    }

    @Override
    public LatticeElement mul(IMathTransformer transformer) {
        return transformer.mulNegativeInf((NegativeInf) this);
    }

    @Override
    public LatticeElement div(IMathTransformer transformer) {
        return transformer.divNegativeInf((NegativeInf) this);
    }

    @Override
    public LatticeElement mod(IMathTransformer transformer) {
        return transformer.modNegativeInf((NegativeInf) this);
    }

    @Override
    public LatticeElement join(IJoinMeetTransformer transformer) {
        return transformer.joinNegativeInf((NegativeInf) this);
    }

    @Override
    public LatticeElement meet(IJoinMeetTransformer transformer) {
        return transformer.meetNegativeInf((NegativeInf) this);
    }

    @Override
    public LatticeElement widen(IJoinMeetTransformer transformer) {
        return transformer.widenNegativeInf((NegativeInf) this);
    }

    @Override
    public LatticeElement joinInterval(Interval other) {
        return new NegativeInf(IntConstant.v(Math.max(
                ((NegativeInf) this).high.value, other.high.value)));
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
        return new Top();
    }

    @Override
    public LatticeElement joinNegativeInf(NegativeInf other) {
        return new NegativeInf(IntConstant.v(Math.max(
                ((NegativeInf) this).high.value, other.high.value)));
    }

    @Override
    public LatticeElement widenInterval(Interval other) {
        NegativeInf thisNeg = ((NegativeInf) this);
        if (thisNeg.high.value > other.high.value) {
            return new Top();
        } else {
            return new NegativeInf(other.high);
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
        return new Top();
    }

    @Override
    public LatticeElement widenNegativeInf(NegativeInf other) {
        NegativeInf thisNeg = ((NegativeInf) this);
        if (thisNeg.high.value > other.high.value) {
            return new Top();
        } else {
            return other;
        }
    }

    @Override
    public LatticeElement meetInterval(Interval other) {
        return other.meetNegativeInf((NegativeInf)this);
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
        return other.meetNegativeInf((NegativeInf)this);
    }

    @Override
    public LatticeElement meetNegativeInf(NegativeInf other) {
    	int b = Math.min(((NegativeInf) this).high.value, other.high.value); 
    	return new NegativeInf(b);
    }

}
