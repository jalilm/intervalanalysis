package intervalAnalysis;

public class Top implements VarState {

    @Override
    public VarState join(VarState varState) {
        return this;
    }


}
