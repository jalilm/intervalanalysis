package intervalAnalysis;

public interface VarState {

    VarState join(VarState varState);

}
