package intervalAnalysis;

public class Bottom implements State {

    @Override
    public void copy(State dest) {
        dest = new Bottom();
    }

}
