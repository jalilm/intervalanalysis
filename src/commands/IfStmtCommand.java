package commands;

import intervalAnalysis.State;





import java.util.Iterator;
import java.util.List;

import abstraction.operations.AbstractLogicOperation;
import abstraction.operations.EqOp;
import abstraction.operations.GeOp;
import abstraction.operations.GtOp;
import abstraction.operations.ILogicOperation;
import abstraction.operations.LeOp;
import abstraction.operations.LtOp;
import abstraction.operations.NeqOp;
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
		State in = inState;
		ILogicOperation logicalOp = null;
		
		JIfStmt fs = (JIfStmt) stmt;
        Value condition = fs.getCondition();
        if (condition instanceof JEqExpr || condition instanceof JNeExpr ||
            condition instanceof JGeExpr || condition instanceof JLeExpr ||
            condition instanceof JLtExpr || condition instanceof JGtExpr)
        {
        	System.out.println(this.getClass() + ": " + fs.toString());
        } 
        
        switch (condition.getClass().getName())
        {

        case "soot.jimple.internal.JNeExpr" : logicalOp = new NeqOp(); break;
        case "soot.jimple.internal.JGeExpr" : logicalOp = new GeOp(); break;
        case "soot.jimple.internal.JLeExpr" : logicalOp = new LeOp(); break;
        case "soot.jimple.internal.JLtExpr" : logicalOp = new LtOp(); break;
        case "soot.jimple.internal.JGtExpr" : logicalOp = new GtOp(); break;
        case "soot.jimple.internal.JEqExpr" : logicalOp = new EqOp(); break;
        }
       
        AbstractBinopExpr expr = (AbstractBinopExpr) condition;
        Value expLeft = expr.getOp1();
        Value expRight = expr.getOp2();
        
        State truePathOut = logicalOp.op(in, expLeft, expRight);
        State falsePathOut = logicalOp.negate(in, expLeft, expRight);
        
        //OUT = IN - KILL + GEN
        truePathOut = in.merge(truePathOut);
        falsePathOut = in.merge(falsePathOut);
        
        //True path
        for (State s : branchOut)
		{
			truePathOut.copy(s);
		}
        
        //False path
        for (State s : fallOut)
     		{
        		falsePathOut.copy(s);
     		}

	}


}
