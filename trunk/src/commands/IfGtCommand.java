package commands;

import intervalAnalysis.State;

import java.util.Iterator;
import java.util.List;

import soot.Value;
import soot.jimple.IfStmt;
import soot.jimple.IntConstant;
import soot.jimple.internal.AbstractBinopExpr;
import soot.jimple.internal.JimpleLocal;

public class IfGtCommand extends IfStmtCommand {

	public IfGtCommand(IfStmt stmt, State inState, List<State> fallOut,
			List<State> branchOut) {
		super(stmt, inState, fallOut, branchOut);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute() {
		IfStmt f = (IfStmt)stmt;
		AbstractBinopExpr expr = (AbstractBinopExpr) f.getCondition();
		Value left  = expr.getOp1();		 
        Value right = expr.getOp2();
		try {
			
        if (left instanceof IntConstant)
        {
            int v = ((IntConstant) left).value;
        }
        else if (left instanceof JimpleLocal)
        {
        	String var = ((JimpleLocal) left).getName();    
        }
            
		else
			throw new Exception("left: unexpected:" + left.getClass() + " name:" + ((JimpleLocal) left).getName());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if ((left instanceof IntConstant) && (right instanceof IntConstant))
		{
			/*
			State trueOut  = new IfTrueTransformer().ifGt(inState, (IntConstant)left, (IntConstant)right);
			State falseOut = new IfFalseTransformer().ifGt(inState, (IntConstant)left, (IntConstant)right);
			*/ 
		}
		
		 for( Iterator it = fallOut.iterator(); it.hasNext(); ) 		 
         {
              State op = (State) it.next();
              if (op != null)
              {
                     System.out.println(op.toString());
              }
         }  

	}
}
