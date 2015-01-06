package intervalAnalysis;

public class Interval implements State {
    final int low;
    final int high;

    public Interval(int low, int high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public void copy(State dest) {
        dest = new Interval(low, high);
    }
}
