package abstraction;

import soot.jimple.IntConstant;
import transform.IJoinMeetTransformer;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public abstract class AbstractInterval implements LatticeElement {

    @Override
    public void joinInterval(Interval other, LatticeElement result) {
        result = new Interval(IntConstant.v(Math.min(
                ((Interval) this).low.value, other.low.value)),
                IntConstant.v(Math.max(((Interval) this).high.value,
                        other.high.value)));
    }

    @Override
    public void joinTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void joinBottom(Bottom other, LatticeElement result) {
        // TODO check correctness
        result = this;
    }

    @Override
    public void joinPositiveInf(PositiveInf other, LatticeElement result) {
        result = new PositiveInf(IntConstant.v(Math.min(
                ((Interval) this).low.value, (other.low.value))));
    }

    @Override
    public void joinNegativeInf(NegativeInf other, LatticeElement result) {
        result = new NegativeInf(IntConstant.v(Math.max(
                ((Interval) this).high.value, (other.high.value))));

    }

    @Override
    public LatticeElement add(IMathTransformer transformer) {
        LatticeElement result = null;
        transformer.addInterval((Interval) this, result);
        return result;
    }

    @Override
    public LatticeElement sub(IMathTransformer transformer) {
        LatticeElement result = null;
        transformer.subInterval((Interval) this, result);
        return result;
    }

    @Override
    public LatticeElement mul(IMathTransformer transformer) {
        LatticeElement result = null;
        transformer.mulInterval((Interval) this, result);
        return result;
    }

    @Override
    public LatticeElement div(IMathTransformer transformer) {
        LatticeElement result = null;
        transformer.divInterval((Interval) this, result);
        return result;
    }

    @Override
    public LatticeElement mod(IMathTransformer transformer) {
        LatticeElement result = null;
        transformer.modInterval((Interval) this, result);
        return result;
    }

    @Override
    public LatticeElement join(IJoinMeetTransformer transformer) {
        LatticeElement result = null;
        transformer.joinInterval((Interval) this, result);
        return result;
    }

    @Override
    public LatticeElement meet(IJoinMeetTransformer transformer) {
        LatticeElement result = null;
        transformer.meetInterval((Interval) this, result);
        return result;
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

}
