package intervalAnalysis;

public class NegativeInf implements VarState {
    final int high;

    public NegativeInf(int high) {
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
            res = new NegativeInf(Math.max(this.high, ((Interval)varState).high));        
        } else if (varState instanceof PositiveInf) {
            res = new Top();
        } else if (varState instanceof NegativeInf) {
            res = new NegativeInf(Math.max(this.high, ((NegativeInf)varState).high));
        } else {
            assert false;
        }
        return res;
    }

}
