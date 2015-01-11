package commands;

import intervalAnalysis.State;

import java.util.List;

import abstraction.operations.EqOp;
import soot.Value;
import soot.jimple.IntConstant;
import soot.jimple.TableSwitchStmt;


public class TableSwitchCommand extends StmtCommand {

	public TableSwitchCommand(TableSwitchStmt stmt, State inState, List<State> fallOut,
			List<State> branchOut) {
		super(stmt, inState, fallOut, branchOut);
	}

	@Override
	public void execute() {
		TableSwitchStmt tableSwitchStmt = (TableSwitchStmt)stmt;
		Value key = tableSwitchStmt.getKey();
		
		//TargetCount is number of cases, except the default
		int low = tableSwitchStmt.getLowIndex();
		int high = tableSwitchStmt.getHighIndex();
		int numberOfCases = high - low + 1 ;
		for (int index = 0; index < numberOfCases; index++ )
		{
			State out = inState.clone();
			int a = low + index;
			State afterLookup = new EqOp().op(inState, key, IntConstant.v(a));
			afterLookup.copy(branchOut.get(index));
		}
		
		//check for default
		if (tableSwitchStmt.getDefaultTarget() != null)
		{
			int indexOfDefaultBranch = numberOfCases;
			inState.copy(branchOut.get(indexOfDefaultBranch));
		}
		
	}

}
