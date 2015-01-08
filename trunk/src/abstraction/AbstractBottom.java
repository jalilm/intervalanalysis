package abstraction;

import transform.IJoinMeetTransformer;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public abstract class AbstractBottom implements LatticeElement {

    @Override
    public LatticeElement add(IMathTransformer transformer) {
        return transformer.addBottom((Bottom) this);
    }

    @Override
    public LatticeElement sub(IMathTransformer transformer) {
        return transformer.subBottom((Bottom) this);
    }

    @Override
    public LatticeElement mul(IMathTransformer transformer) {
        return transformer.mulBottom((Bottom) this);
    }

    @Override
    public LatticeElement div(IMathTransformer transformer) {
        return transformer.divBottom((Bottom) this);
    }

    @Override
    public LatticeElement mod(IMathTransformer transformer) {
        return transformer.modBottom((Bottom) this);
    }

    @Override
    public LatticeElement join(IJoinMeetTransformer transformer) {
        return transformer.joinBottom((Bottom) this);
    }

    @Override
    public LatticeElement meet(IJoinMeetTransformer transformer) {
        return transformer.meetBottom((Bottom) this);
    }

    @Override
    public LatticeElement widen(IJoinMeetTransformer transformer) {
        return transformer.widenBottom((Bottom) this);
    }

    @Override
    public LatticeElement joinInterval(Interval other) {
        return other;
    }

    @Override
    public LatticeElement joinTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement joinBottom(Bottom other) {
        return other;
    }

    @Override
    public LatticeElement joinPositiveInf(PositiveInf other) {
        return other;
    }

    @Override
    public LatticeElement joinNegativeInf(NegativeInf other) {
        return other;
    }

    @Override
    public LatticeElement widenInterval(Interval other) {
        return other;
    }

    @Override
    public LatticeElement widenTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement widenBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement widenPositiveInf(PositiveInf other) {
        return other;
    }

    @Override
    public LatticeElement widenNegativeInf(NegativeInf other) {
        return other;
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
