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
		// TODO Auto-generated method stub
		LookupSwitchStmt switchStmt = (LookupSwitchStmt) stmt;
		Value key = switchStmt.getKey();
		
		int indexOfDefaultBranch = switchStmt.getTargetCount();
		//TargetCount is number of cases, +1 for default
		for (int index = 0; index < switchStmt.getTargetCount()+1; index++ )
		{
			State out = new State();
			inState.copy(out);
			
			//handle default
			if (index == indexOfDefaultBranch)
			{
				out.copy(branchOut.get(indexOfDefaultBranch));
				continue;
			}
			
			int a = switchStmt.getLookupValue(index);
			State afterLookup = new EqOp().op(inState, key, IntConstant.v(a));
			afterLookup.copy(branchOut.get(index));
		}
		

	}

}
