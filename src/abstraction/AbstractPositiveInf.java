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

        return transformer.joinPositiveInf((PositiveInf) this);

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

}
