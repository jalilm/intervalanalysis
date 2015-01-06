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
        VarState res = null;
        if (varState instanceof Top) {
            res = new Top();
        } else if (varState instanceof Bottom) {
            res = this;
        } else if (varState instanceof Interval) {
            res = new Interval(Math.min(this.low, ((Interval)varState).low),
                                Math.max(this.high, ((Interval)varState).high));        
        } else if (varState instanceof PositiveInf) {
            res = new PositiveInf(Math.min(this.low, ((PositiveInf)varState).low));
        } else if (varState instanceof NegativeInf) {
            res = new PositiveInf(Math.max(this.high, ((NegativeInf)varState).high));
        } else {
            assert false;
        }
        return res;
    }
}
