package intervalAnalysis;

import soot.jimple.IntConstant;

public class NegativeInf implements LatticeElement {
    final IntConstant high;

    public NegativeInf(IntConstant high) {
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
            res = new NegativeInf(IntConstant.v(Math.max(this.high.value, ((Interval)varState).high.value)));        
        } else if (varState instanceof PositiveInf) {
            res = new Top();
        } else if (varState instanceof NegativeInf) {
            res = new NegativeInf(IntConstant.v(Math.max(this.high.value, ((NegativeInf)varState).high.value)));
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
