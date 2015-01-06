package intervalAnalysis;

public class Bottom implements VarState {

    @Override
    public VarState join(VarState varState) {
        return varState;
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
