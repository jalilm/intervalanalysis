package abstraction;

import transform.IJoinMeetTransformer;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public interface LatticeElement extends IMathTransformer, ILogicalTransformer,
        IJoinMeetTransformer {

    LatticeElement add(IMathTransformer transformer);

    LatticeElement sub(IMathTransformer transformer);

    LatticeElement mul(IMathTransformer transformer);

    LatticeElement div(IMathTransformer transformer);

    LatticeElement mod(IMathTransformer transformer);

    LatticeElement eq(ILogicalTransformer transformer);

    LatticeElement neq(ILogicalTransformer transformer);

    LatticeElement gt(ILogicalTransformer transformer);

    LatticeElement ge(ILogicalTransformer transformer);

    LatticeElement lt(ILogicalTransformer transformer);

    LatticeElement le(ILogicalTransformer transformer);

    LatticeElement join(IJoinMeetTransformer varState);

    LatticeElement meet(IJoinMeetTransformer varState);

    LatticeElement widen(IJoinMeetTransformer currElement);
}
