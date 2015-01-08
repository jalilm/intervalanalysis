package abstraction;

import transform.IJoinMeetTransformer;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public abstract class TopAbstract implements LatticeElement {

    @Override
    public LatticeElement add(IMathTransformer transformer) {
        return transformer.addTop((Top) this);
    }

    @Override
    public LatticeElement sub(IMathTransformer transformer) {
        return transformer.subTop((Top) this);
    }

    @Override
    public LatticeElement mul(IMathTransformer transformer) {
        return transformer.mulTop((Top) this);
    }

    @Override
    public LatticeElement div(IMathTransformer transformer) {
        return transformer.divTop((Top) this);
    }

    @Override
    public LatticeElement mod(IMathTransformer transformer) {
        return transformer.modTop((Top) this);
    }

    @Override
    public LatticeElement join(IJoinMeetTransformer transformer) {
        return transformer.joinTop((Top) this);
    }

    @Override
    public LatticeElement meet(IJoinMeetTransformer transformer) {
        return transformer.meetTop((Top) this);
    }

    @Override
    public LatticeElement widen(IJoinMeetTransformer transformer) {
        return transformer.widenTop((Top) this);
    }

    @Override
    public LatticeElement joinInterval(Interval other) {
        return this;
    }

    @Override
    public LatticeElement joinTop(Top other) {
        return this;
    }

    @Override
    public LatticeElement joinBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement joinPositiveInf(PositiveInf other) {
        return this;
    }

    @Override
    public LatticeElement joinNegativeInf(NegativeInf other) {
        return this;
    }

    @Override
    public LatticeElement widenInterval(Interval other) {
        return this;
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
        return this;
    }

    @Override
    public LatticeElement widenNegativeInf(NegativeInf other) {
        return this;
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
