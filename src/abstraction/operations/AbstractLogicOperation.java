package abstraction.operations;

import soot.Local;
import soot.Value;
import soot.jimple.IntConstant;
import intervalAnalysis.State;

public abstract class AbstractLogicOperation {
    public abstract State op(State in, IntConstant left, IntConstant right);

    public abstract State op(State in, Local left, IntConstant right);

    public abstract State op(State in, IntConstant left, Local right);

    public abstract State op(State in, Local left, Local right);

    public abstract State negate(State in, Value left, Value right);

    public State op(State in, Value left, Value right) {
        if (left.getClass().equals(IntConstant.class)
                && right.getClass().equals(IntConstant.class)) {
            return this.op(in, (IntConstant) left, (IntConstant) right);
        } else if (left.getClass().equals(IntConstant.class)
                && right.getClass().equals(Local.class)) {
            return this.op(in, (IntConstant) left, (Local) right);
        } else if (left.getClass().equals(Local.class)
                && right.getClass().equals(IntConstant.class)) {
            return this.op(in, (Local) left, (IntConstant) right);
        } else if (left.getClass().equals(Local.class)
                && right.getClass().equals(Local.class)) {
            return this.op(in, (Local) left, (Local) right);
        } else {
            assert false;
            return in;
        }
    }

}
