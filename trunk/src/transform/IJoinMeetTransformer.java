package transform;

import intervalAnalysis.Bottom;
import intervalAnalysis.Interval;
import intervalAnalysis.LatticeElement;
import intervalAnalysis.NegativeInf;
import intervalAnalysis.PositiveInf;
import intervalAnalysis.Top;

public interface IJoinMeetTransformer {
	void joinInterval(Interval other, LatticeElement result);
	void joinTop(Top other, LatticeElement result);
	void joinBottom(Bottom other, LatticeElement result);
	void joinPositiveInf(PositiveInf other, LatticeElement result);
	void joinNegativeInf(NegativeInf other, LatticeElement result);
	
	void meetInterval(Interval other, LatticeElement result);
	void meetTop(Top other, LatticeElement result);
	void meetBottom(Bottom other, LatticeElement result);
	void meetPositiveInf(PositiveInf other, LatticeElement result);
	void meetNegativeInf(NegativeInf other, LatticeElement result);
}
