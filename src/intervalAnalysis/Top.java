package intervalAnalysis;

public class Top implements State {

    @Override
    public void copy(State dest) {
        dest = new Top(); 
    }

}
