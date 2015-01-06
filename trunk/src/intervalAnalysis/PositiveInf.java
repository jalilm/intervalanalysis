package intervalAnalysis;

public class PositiveInf implements VarState {
    final int low;

    public PositiveInf(int low) {
        this.low = low;
    }

    @Override
    public VarState join(VarState varState) {
        // TODO Auto-generated method stub
        return null;
    }

}
