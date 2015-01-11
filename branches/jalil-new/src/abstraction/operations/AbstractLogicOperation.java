package abstraction.operations;

import intervalAnalysis.State;

import soot.Local;
import soot.Value;
import soot.jimple.IntConstant;

public abstract class AbstractLogicOperation implements ILogicOperation {

    @Override
    public State op(State in, Value left, Value right) {

        if (!(left instanceof Local) && !(right instanceof Local)) {
            // there are no locals
            return op(in, (IntConstant) left, (IntConstant) right);
        } else if ((left instanceof Local) && !(right instanceof Local)) {
            // left local
            return op(in, (Local) left, (IntConstant) right);
        } else if (!(left instanceof Local) && (right instanceof Local)) {
            // right local
            return op(in, (IntConstant) left, (Local) right);
        } else if ((left instanceof Local) && (right instanceof Local)) {
            // two locals
            return op(in, (Local) left, (Local) right);
        } else {
            // Error
            assert false;
        }

        return null;
    }

    abstract public State op(State in, Local left, Local right);

    abstract public State op(State in, IntConstant left, Local right);

    abstract public State op(State in, Local left, IntConstant right);

    abstract public State op(State in, IntConstant left, IntConstant right);
}
