package intervalAnalysis;

public class Bottom implements VarState {

    @Override
    public VarState join(VarState varState) {
        return varState;
    }

}
