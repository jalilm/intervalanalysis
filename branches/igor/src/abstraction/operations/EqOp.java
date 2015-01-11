package abstraction.operations;

import intervalAnalysis.State;

import java.util.List;

import abstraction.LatticeElement;
import commands.StmtCommand;
import soot.Local;
import soot.Value;
import soot.jimple.IfStmt;
import soot.jimple.IntConstant;

public class EqOp implements ILogicOperation {

	@Override
	public State op(State in, Value left, Value right) {
		State in1 = in.clone();
		State in2 = in.clone();
		State temp1 = new GeOp().op(in1, left, right);
		State temp2 = new GeOp().op(in2, right, left);
		State temp3 = temp1.meet(temp2); 
		return temp3;
	}

	@Override
	public State negate(State in, Value left, Value right) {
		return new NeqOp().op(in,left,right);
	}

}
