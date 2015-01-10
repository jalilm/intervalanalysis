package abstraction.operations;

import intervalAnalysis.State;

import java.util.List;

import commands.IfStmtCommand;
import soot.Local;
import soot.Value;
import soot.jimple.IfStmt;
import soot.jimple.IntConstant;

public class LtOp implements ILogicOperation {

	@Override
	public State op(State in, Value left, Value right) {
		return new GtOp().op(in, right, left);
	}

	@Override
	public State negate(State in, Value left, Value right) {
		return new GeOp().op(in, left, right);
	}

}
