package abstraction.operations;

import intervalAnalysis.State;

import abstraction.LatticeElement;
import abstraction.NegativeInf;
import abstraction.PositiveInf;

import soot.Local;
import soot.Value;

import soot.jimple.IntConstant;

public class GeOp extends AbstractLogicOperation {

    public State op(State in, IntConstant left, IntConstant right) {
        if (left.value >= right.value) {
            return in;
        } else {
            return new State();
        }
    }

    public State op(State in, Local left, IntConstant right) {
        LatticeElement newX = new PositiveInf(right.value);
        State out = new State();
        out.setVarState((Value) left, newX);
        return in.meet(out);
    }

    public State op(State in, IntConstant left, Local right) {
        LatticeElement newX = new NegativeInf(left.value);
        State out = new State();
        out.setVarState((Value) right, newX);
        return in.meet(out);
    }

    public State op(State in, Local left, Local right) {
        LatticeElement x = in.getVarState(left);
        LatticeElement y = in.getVarState(right);

        LatticeElement newX = y.createLowToPositiveInf();
        LatticeElement newY = x.createNegativeInfToHigh();

        State out = new State();
        out.setVarState((Value) left, newX);
        out.setVarState((Value) right, newY);
        return in.meet(out);
    }

    @Override
    public State negate(State in, Value left, Value right) {
        return new LtOp().op(in, left, right);
    }

}
