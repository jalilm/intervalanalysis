package intervalAnalysis;

public class PositiveInf implements State {
    final int low;

    public PositiveInf(int low) {
        this.low = low;
    }

    @Override
    public void copy(State dest) {
        dest = new PositiveInf(low);
    }
}
