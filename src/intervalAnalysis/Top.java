package intervalAnalysis;

public class Top implements VarState {

    @Override
    public VarState join(VarState varState) {
        return this;
    }

    @Override
    public VarState sub(VarState op2State) {
        return this;
    }

    @Override
    public VarState add(VarState op2State) {
        return this;
    }

    @Override
    public VarState mul(VarState op2State) {
        return this;
    }

    @Override
    public VarState div(VarState op2State) {
        return this;
    }

    @Override
    public VarState rem(VarState op2State) {
        return this;
    }


}
