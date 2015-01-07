package commands;

import intervalAnalysis.State;
import java.util.List;
import soot.jimple.IfStmt;

public class IfEqCommand extends IfStmtCommand {

	public IfEqCommand(IfStmt stmt, State inState, List<State> fallOut,
			List<State> branchOut) {
		super(stmt, inState, fallOut, branchOut);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}
