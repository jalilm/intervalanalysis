package intervalAnalysis;

public class PositiveInf implements VarState {
    final int low;

    public PositiveInf(int low) {
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
            res = new PositiveInf(Math.min(this.low, ((Interval)varState).low));        
        } else if (varState instanceof NegativeInf) {
            res = new Top();
        } else if (varState instanceof PositiveInf) {
            res = new PositiveInf(Math.min(this.low, ((PositiveInf)varState).low));
        } else {
            assert false;
        }
        return res;
    }
}
