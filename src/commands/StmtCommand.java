package commands;

import intervalAnalysis.State;

import java.util.List;

import soot.Unit;
import soot.jimple.Stmt;

public abstract class StmtCommand {
	Unit stmt = null;
	State inState = null;
	List<State> fallOut = null;
	List<State> branchOut = null;
	
	public StmtCommand(Stmt stmt, State inState, List<State> fallOut,
			List<State> branchOut) {
		this.stmt = stmt;
		this.inState = inState;
		this.fallOut = fallOut;
		this.branchOut = branchOut;
	}
	
	public abstract void execute();
}
