package commands;

import intervalAnalysis.State;

import java.util.List;

import soot.jimple.IfStmt;

public class IfLeCommand extends IfStmtCommand {

	public IfLeCommand(IfStmt stmt, State inState, List<State> fallOut,
			List<State> branchOut) {
		super(stmt, inState, fallOut, branchOut);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}
}