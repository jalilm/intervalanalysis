package intervalAnalysis;

import soot.jimple.IntConstant;

public class PositiveInf implements VarState {
    final IntConstant low;

    public PositiveInf(IntConstant low) {
        this.low = low;
    }

    @Override
    public VarState join(VarState varState) {
        VarState res = null;
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
    public VarState sub(VarState op2State) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VarState add(VarState op2State) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VarState mul(VarState op2State) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VarState div(VarState op2State) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VarState rem(VarState op2State) {
        // TODO Auto-generated method stub
        return null;
    }
}
