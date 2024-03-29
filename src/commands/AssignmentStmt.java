package commands;

import intervalAnalysis.State;

import java.util.List;

import abstraction.Interval;
import abstraction.LatticeElement;
import abstraction.Top;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.NumericConstant;
import soot.jimple.IntConstant;
import soot.jimple.internal.AbstractJimpleFloatBinopExpr;
import soot.jimple.internal.JAddExpr;
import soot.jimple.internal.JDivExpr;
import soot.jimple.internal.JMulExpr;
import soot.jimple.internal.JNegExpr;
import soot.jimple.internal.JRemExpr;
import soot.jimple.internal.JSubExpr;
import soot.jimple.internal.JimpleLocal;

public class AssignmentStmt extends StmtCommand {

    public AssignmentStmt(AssignStmt stmt, State inState, List<State> fallOut,
            List<State> branchOut) {
        super(stmt, inState, fallOut, branchOut);
    }

    public void execute() {
        LatticeElement rightOpState = null;
        AssignStmt AStmt = (AssignStmt) stmt;
        Value leftOp = AStmt.getLeftOp();
        Value rightOp = AStmt.getRightOp();
        if (rightOp instanceof NumericConstant) {
            rightOpState = new Interval((IntConstant) rightOp,
                    (IntConstant) rightOp);
        } else if(rightOp instanceof JimpleLocal){
            rightOpState = this.inState
                    .getVarState(rightOp);
        } else if (rightOp instanceof AbstractJimpleFloatBinopExpr) {
            LatticeElement op1State = this.inState
                    .getVarState((((AbstractJimpleFloatBinopExpr) rightOp)
                            .getOp1()));

            LatticeElement op2State = this.inState
                    .getVarState((((AbstractJimpleFloatBinopExpr) rightOp)
                            .getOp2()));
            if (rightOp instanceof JSubExpr) {
            	rightOpState = op1State.sub(op2State);
            } else if (rightOp instanceof JAddExpr) {
                rightOpState = op1State.add(op2State);
            } else if (rightOp instanceof JMulExpr) {
                rightOpState = op1State.mul(op2State);
            } else if (rightOp instanceof JDivExpr) {
                rightOpState = op1State.div(op2State);
            } else if (rightOp instanceof JRemExpr) {
                rightOpState = op1State.mod(op2State);
            } else {
                assert false;
            }
        } else if( rightOp instanceof JNegExpr) {
            LatticeElement negated = this.inState.getVarState(((JNegExpr)rightOp).getOp());
            rightOpState = negated.neg();
        } else {  //JLengthStmt
        	rightOpState = new Top();
        }
        
        State out = inState.clone();
        out.setVarState(leftOp, rightOpState);
        out.copy(fallOut.get(0));

    };

}
