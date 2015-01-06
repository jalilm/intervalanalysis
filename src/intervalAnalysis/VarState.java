package intervalAnalysis;


public interface VarState {

    VarState join(VarState varState);

    VarState sub(VarState op2State);

    VarState add(VarState op2State);

    VarState mul(VarState op2State);

    VarState div(VarState op2State);

    VarState rem(VarState op2State);

}
