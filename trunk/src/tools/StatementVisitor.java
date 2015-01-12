package tools;

import java.util.List;

import commands.AssignmentStmt;
import commands.IfStmtCommand;
import commands.LookSwitchCommand;
import commands.TableSwitchCommand;
import intervalAnalysis.State;
import soot.Unit;
import soot.jimple.AssignStmt;
import soot.jimple.IfStmt;
import soot.jimple.LookupSwitchStmt;
import soot.jimple.TableSwitchStmt;

public class StatementVisitor {
	public void visit(Unit stmt, State inState, List<State> fallOut, List<State> BranchOut)
	{
	    if(inState.isBottom()) {
	        defaultCase(stmt,inState,fallOut,BranchOut);
	        return;
	    }
		switch (stmt.getClass().getName())
		{
		case "soot.jimple.internal.JIfStmt":  		  caseIfStmt((IfStmt)stmt,inState,fallOut,BranchOut); break;
		case "soot.jimple.internal.JAssignStmt":  	  caseAssignStmt((AssignStmt)stmt,inState,fallOut,BranchOut); break; 
		case "soot.jimple.internal.JTableSwitchStmt": caseTableSwitchStmt((TableSwitchStmt)stmt,inState,fallOut,BranchOut); break;
		case "soot.jimple.internal.JLookupSwitchStmt": caseLookupSwitchStmt((LookupSwitchStmt)stmt,inState,fallOut,BranchOut); break;
		default: defaultCase(stmt,inState,fallOut,BranchOut);
		}
	}

	
	public void caseAssignStmt(AssignStmt stmt, State inState, List<State> fallOut, List<State> branchOut) 
	{
		new AssignmentStmt(stmt,inState,fallOut,branchOut).execute();
	}
		
	public void caseIfStmt(IfStmt stmt, State inState, List<State> fallOut, List<State> branchOut) {
		new IfStmtCommand(stmt,inState,fallOut,branchOut).execute();
	}

	public void caseLookupSwitchStmt(LookupSwitchStmt stmt, State inState, List<State> fallOut, List<State> branchOut) {
		new LookSwitchCommand((LookupSwitchStmt)stmt,inState,fallOut,branchOut).execute();		
	}
	
	public void caseTableSwitchStmt(TableSwitchStmt stmt, State inState, List<State> fallOut, List<State> branchOut) {
		new TableSwitchCommand((TableSwitchStmt)stmt,inState,fallOut,branchOut).execute();
		
	}

	public void defaultCase(Unit stmt, State inState, List<State> fallOut, List<State> branchOut) {
        //copy in to out
        for (State s : fallOut)
            {
                inState.copy(s);
            }
        
        for (State s : branchOut)
            {
                inState.copy(s);
            }
	}
}
