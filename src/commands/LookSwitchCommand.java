package commands;

import intervalAnalysis.State;

import java.util.List;

import abstraction.operations.EqOp;
import soot.Value;
import soot.jimple.IntConstant;
import soot.jimple.LookupSwitchStmt;

public class LookSwitchCommand extends StmtCommand {

	public LookSwitchCommand(LookupSwitchStmt stmt, State inState, List<State> fallOut,
			List<State> branchOut) {
		super(stmt, inState, fallOut, branchOut);
	}

	@Override
	public void execute() {

		LookupSwitchStmt switchStmt = (LookupSwitchStmt) stmt;
		Value key = switchStmt.getKey();
		

		//TargetCount is number of cases, except the default
		for (int index = 0; index < switchStmt.getTargetCount(); index++ )
		{
			int a = switchStmt.getLookupValue(index);
			State afterLookup = new EqOp().op(inState, key, IntConstant.v(a));
			afterLookup.copy(branchOut.get(index));
		}
		
		//check for default
		if (switchStmt.getDefaultTarget() != null)
		{
			int indexOfDefaultBranch = switchStmt.getTargetCount();
			inState.copy(branchOut.get(indexOfDefaultBranch));
		}
		

	}

}
