package abstraction;

public class Bottom implements LatticeElement {

    @Override
    public LatticeElement join(LatticeElement varState) {
        return varState;
    }

    @Override
    public LatticeElement sub(LatticeElement op2State) {
        return this;
    }

    @Override
    public LatticeElement add(LatticeElement op2State) {
        return this;
    }

    @Override
    public LatticeElement mul(LatticeElement op2State) {
        return this;
    }

    @Override
    public LatticeElement div(LatticeElement op2State) {
        return this;
    }

    @Override
    public LatticeElement rem(LatticeElement op2State) {
        return this;
    }

}
