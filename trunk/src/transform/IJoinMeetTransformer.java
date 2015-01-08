package transform;

import abstraction.Bottom;
import abstraction.Interval;
import abstraction.LatticeElement;
import abstraction.NegativeInf;
import abstraction.PositiveInf;
import abstraction.Top;

public interface IJoinMeetTransformer {
	LatticeElement joinInterval(Interval other);
	LatticeElement joinTop(Top other);
	LatticeElement joinBottom(Bottom other);
	LatticeElement joinPositiveInf(PositiveInf other);
	LatticeElement joinNegativeInf(NegativeInf other);
	
	LatticeElement meetInterval(Interval other);
	LatticeElement meetTop(Top other);
	LatticeElement meetBottom(Bottom other);
	LatticeElement meetPositiveInf(PositiveInf other);
	LatticeElement meetNegativeInf(NegativeInf other);
	
	LatticeElement widenInterval(Interval other);
    LatticeElement widenTop(Top other);
    LatticeElement widenBottom(Bottom other);
    LatticeElement widenPositiveInf(PositiveInf other);
    LatticeElement widenNegativeInf(NegativeInf other);
}
