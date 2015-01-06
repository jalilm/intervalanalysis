package commands;

import intervalAnalysis.State;

import java.util.List;

import soot.Value;
import soot.jimple.IfStmt;
import soot.jimple.internal.*;

public class IfStmtCommand extends StmtCommand{

	public IfStmtCommand(IfStmt stmt, State inState, List<State> fallOut,
			List<State> branchOut) {
		super(stmt, inState, fallOut, branchOut);
	}

	@Override
	public void execute() {
		JIfStmt fs = (JIfStmt) stmt;
        Value condition = fs.getCondition();
        if (condition instanceof JEqExpr || condition instanceof JNeExpr ||
            condition instanceof JGeExpr || condition instanceof JLeExpr ||
            condition instanceof JLtExpr || condition instanceof JGtExpr)
        {
        	System.out.println(this.getClass() + ": " + fs.toString());
        } 

	}

}
