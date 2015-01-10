package abstraction;

import java.util.List;

import transform.IBuilderTransformer;
import transform.IJoinMeetTransformer;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public interface LatticeElement extends IMathTransformer, ILogicalTransformer,
        IJoinMeetTransformer, IBuilderTransformer {
	
    LatticeElement add(IMathTransformer transformer);

    LatticeElement sub(IMathTransformer transformer);

    LatticeElement mul(IMathTransformer transformer);

    LatticeElement div(IMathTransformer transformer);

    LatticeElement mod(IMathTransformer transformer);

    LatticeElement join(IJoinMeetTransformer varState);

    LatticeElement meet(IJoinMeetTransformer varState);

    LatticeElement widen(IJoinMeetTransformer currElement);
    

}
