package transform;

import soot.Local;
import soot.jimple.IntConstant;
import intervalAnalysis.Bottom;
import intervalAnalysis.Interval;
import intervalAnalysis.LatticeElement;
import intervalAnalysis.NegativeInf;
import intervalAnalysis.PositiveInf;
import intervalAnalysis.State;
import intervalAnalysis.Top;

public interface IMathTransformer{
	//arithmetic commands
	void addInterval(Interval other, LatticeElement result);
	void addTop(Top other, LatticeElement result);
	void addBottom(Bottom other, LatticeElement result);
	void addPositiveInf(PositiveInf other, LatticeElement result);
	void addNegativeInf(NegativeInf other, LatticeElement result);
	
	/*
	void subtract(LatticeElement other, LatticeElement result);
	void multiply(LatticeElement other, LatticeElement result);
	void divide(LatticeElement other, LatticeElement result);
	void mod(LatticeElement other, LatticeElement result);
	*/
}
