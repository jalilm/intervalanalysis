package abstraction;

import soot.jimple.IntConstant;

public class PositiveInf implements LatticeElement {
    final IntConstant low;

    public PositiveInf(IntConstant low) {
        this.low = low;
    }

    @Override
    public LatticeElement join(LatticeElement varState) {
        LatticeElement res = null;
        if (varState instanceof Top) {
            res = new Top();
        } else if (varState instanceof Bottom) {
            res = this;
        } else if (varState instanceof Interval) {
            res = new PositiveInf(IntConstant.v(Math.min(this.low.value, ((Interval)varState).low.value)));        
        } else if (varState instanceof NegativeInf) {
            res = new Top();
        } else if (varState instanceof PositiveInf) {
            res = new PositiveInf(IntConstant.v(Math.min(this.low.value, ((PositiveInf)varState).low.value)));
        } else {
            assert false;
        }
        return res;
    }

    @Override
    public LatticeElement sub(LatticeElement op2State) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LatticeElement add(LatticeElement op2State) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LatticeElement mul(LatticeElement op2State) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LatticeElement div(LatticeElement op2State) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LatticeElement rem(LatticeElement op2State) {
        // TODO Auto-generated method stub
        return null;
    }
}
