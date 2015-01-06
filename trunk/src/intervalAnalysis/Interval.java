package intervalAnalysis;

public class Interval implements VarState {
    final int low;
    final int high;

    public Interval(int low, int high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public VarState join(VarState varState) {
        // TODO Auto-generated method stub
        return null;
    }

}
