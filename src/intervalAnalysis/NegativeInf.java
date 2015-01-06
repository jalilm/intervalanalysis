package intervalAnalysis;

public class NegativeInf implements State {
    final int high;

    public NegativeInf(int high) {
        this.high = high;
    }

    @Override
    public void copy(State dest) {
        dest = new NegativeInf(high);
    }
}
