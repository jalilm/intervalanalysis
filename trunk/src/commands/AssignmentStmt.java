package commands;

import intervalAnalysis.State;

import java.util.List;

import abstraction.Interval;
import abstraction.LatticeElement;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.NumericConstant;
import soot.jimple.IntConstant;
import soot.jimple.internal.AbstractJimpleFloatBinopExpr;
import soot.jimple.internal.JAddExpr;
import soot.jimple.internal.JDivExpr;
import soot.jimple.internal.JMulExpr;
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
                    .getLatticeElement(rightOp);
        } else if (rightOp instanceof AbstractJimpleFloatBinopExpr) {
            LatticeElement op1State = this.inState
                    .getLatticeElement((((AbstractJimpleFloatBinopExpr) rightOp)
                            .getOp1()));

            LatticeElement op2State = this.inState
                    .getLatticeElement((((AbstractJimpleFloatBinopExpr) rightOp)
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
        }
        this.inState.updateLatticeElement(leftOp, rightOpState);
        State s = fallOut.get(0).merge(inState);
        fallOut.remove(0);
        fallOut.add(0,s);
    };

}
