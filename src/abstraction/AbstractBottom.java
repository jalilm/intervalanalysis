package abstraction;

import transform.IJoinMeetTransformer;
import transform.IMathTransformer;

public abstract class AbstractBottom implements LatticeElement {
    
    @Override
    public LatticeElement neg() {
        return this;
    }

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
    public LatticeElement meetInterval(Interval other) {
        return this;
    }

    @Override
    public LatticeElement meetTop(Top other) {
        return this;
    }

    @Override
    public LatticeElement meetBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement meetPositiveInf(PositiveInf other) {
        return this;
    }

    @Override
    public LatticeElement meetNegativeInf(NegativeInf other) {
        return this;
    }

}
