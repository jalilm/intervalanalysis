package tools;

import java.util.List;

import commands.AssignmentStmt;
import commands.IfStmtCommand;
import intervalAnalysis.State;
import soot.Unit;
import soot.jimple.AssignStmt;
import soot.jimple.BreakpointStmt;
import soot.jimple.EnterMonitorStmt;
import soot.jimple.ExitMonitorStmt;
import soot.jimple.GotoStmt;
import soot.jimple.IdentityStmt;
import soot.jimple.IfStmt;
import soot.jimple.InvokeStmt;
import soot.jimple.LookupSwitchStmt;
import soot.jimple.NopStmt;
import soot.jimple.RetStmt;
import soot.jimple.ReturnStmt;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.TableSwitchStmt;
import soot.jimple.ThrowStmt;


public class StatementVisitor {
	public void visit(Unit stmt,State inState, List<State> fallOut, List<State> BranchOut)
	{
		switch (stmt.getClass().getName())
		{
		case "soot.jimple.internal.JIfStmt":  		  caseIfStmt((IfStmt)stmt,inState,fallOut,BranchOut); break;
		case "soot.jimple.internal.JAssignStmt":  	  caseAssignStmt((AssignStmt)stmt,inState,fallOut,BranchOut); break; 
		case "soot.jimple.internal.JTableSwitchStmt": caseLookupSwitchStmt((LookupSwitchStmt)stmt,inState,fallOut,BranchOut); break;
		case "soot.jimple.internal.JLookupSwitchStmt": caseLookupSwitchStmt((LookupSwitchStmt)stmt,inState,fallOut,BranchOut); break;
		}
		return;
	}

	
	public void caseAssignStmt(AssignStmt stmt, State inState, List<State> fallOut, List<State> branchOut) 
	{
		new AssignmentStmt(stmt,inState,fallOut,branchOut).execute();
		
	}

	
	public void caseBreakpointStmt(BreakpointStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseEnterMonitorStmt(EnterMonitorStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseExitMonitorStmt(ExitMonitorStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseGotoStmt(GotoStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseIdentityStmt(IdentityStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseIfStmt(IfStmt stmt, State inState, List<State> fallOut, List<State> branchOut) {
		System.out.println("If " + stmt.toString());
		new IfStmtCommand(stmt,inState,fallOut,branchOut).execute();
		
	}

	
	public void caseInvokeStmt(InvokeStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseLookupSwitchStmt(LookupSwitchStmt arg0, State inState, List<State> fallOut, List<State> branchOut) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseNopStmt(NopStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseRetStmt(RetStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseReturnStmt(ReturnStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseReturnVoidStmt(ReturnVoidStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseTableSwitchStmt(TableSwitchStmt arg0, State inState, List<State> fallOut, List<State> branchOut) {
		// TODO Auto-generated method stub
		
	}

	
	public void caseThrowStmt(ThrowStmt arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void defaultCase(Object arg0) {
		// TODO Auto-generated method stub
		
	}
}
