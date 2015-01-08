package abstraction;

import soot.jimple.IntConstant;
import transform.IJoinMeetTransformer;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public abstract class AbstractInterval implements LatticeElement {

    @Override
    public LatticeElement add(IMathTransformer transformer) {
        return transformer.addInterval((Interval) this);
    }

    @Override
    public LatticeElement sub(IMathTransformer transformer) {
        return transformer.subInterval((Interval) this);
    }

    @Override
    public LatticeElement mul(IMathTransformer transformer) {
        return transformer.mulInterval((Interval) this);
    }

    @Override
    public LatticeElement div(IMathTransformer transformer) {
        return transformer.divInterval((Interval) this);
    }

    @Override
    public LatticeElement mod(IMathTransformer transformer) {
        return transformer.modInterval((Interval) this);
    }

    @Override
    public LatticeElement join(IJoinMeetTransformer transformer) {
        return transformer.joinInterval((Interval) this);
    }

    @Override
    public LatticeElement meet(IJoinMeetTransformer transformer) {
        return transformer.meetInterval((Interval) this);
    }

    @Override
    public LatticeElement widen(IJoinMeetTransformer transfromer) {
        return transfromer.widenInterval((Interval) this);
    }

    @Override
    public LatticeElement joinInterval(Interval other) {
        return new Interval(IntConstant.v(Math.min(((Interval) this).low.value,
                other.low.value)), IntConstant.v(Math.max(
                ((Interval) this).high.value, other.high.value)));
    }

    @Override
    public LatticeElement joinTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement joinBottom(Bottom other) {
        // TODO check correctness
        return this;
    }

    @Override
    public LatticeElement joinPositiveInf(PositiveInf other) {
        return new PositiveInf(IntConstant.v(Math.min(
                ((Interval) this).low.value, (other.low.value))));
    }

    @Override
    public LatticeElement joinNegativeInf(NegativeInf other) {
        return new NegativeInf(IntConstant.v(Math.max(
                ((Interval) this).high.value, (other.high.value))));
    }

    @Override
    public LatticeElement widenInterval(Interval other) {
        Interval thisInterval = ((Interval) this);
        if (thisInterval.high.value > other.high.value) {
            if (thisInterval.low.value < other.low.value) {
                return new Top();
            } else {
                return new PositiveInf(other.low);
            }
        } else {
            if (thisInterval.low.value < other.low.value) {
                return new NegativeInf(other.high);
            } else {
                return other;
            }
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
        Interval thisInt = (Interval) this;
        if (thisInt.low.value < other.low.value) {
            return new Top();
        } else {
            return other;
        }
    }

    @Override
    public LatticeElement widenNegativeInf(NegativeInf other) {
        Interval thisInt = (Interval) this;
        if (thisInt.high.value > other.high.value) {
            return new Top();
        } else {
            return other;
        }
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
    public LatticeElement meetInterval(Interval other) {
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public LatticeElement meetTop(Top other) {
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public LatticeElement meetBottom(Bottom other) {
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public LatticeElement meetPositiveInf(PositiveInf other) {
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public LatticeElement meetNegativeInf(NegativeInf other) {
        return null;
        // TODO Auto-generated method stub
    }

}
