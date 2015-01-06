package intervalAnalysis;

public class NegativeInf implements VarState {
    final int high;

    public NegativeInf(int high) {
        this.high = high;
    }

    @Override
    public VarState join(VarState varState) {
        // TODO Auto-generated method stub
        return null;
    }
}
