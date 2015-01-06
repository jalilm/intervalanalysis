package commands;

import intervalAnalysis.Interval;
import intervalAnalysis.State;
import intervalAnalysis.VarState;

import java.util.List;

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

public class AssignmentStmt extends StmtCommand {

    public AssignmentStmt(AssignStmt stmt, State inState, List<State> fallOut,
            List<State> branchOut) {
        super(stmt, inState, fallOut, branchOut);
    }

    public void execute() {
        VarState rightOpState = null;
        AssignStmt AStmt = (AssignStmt) stmt;
        Value leftOp = AStmt.getLeftOp();
        Value rightOp = AStmt.getRightOp();
        if (rightOp instanceof NumericConstant) {
            rightOpState = new Interval((IntConstant) rightOp,
                    (IntConstant) rightOp);
        } else if (rightOp instanceof AbstractJimpleFloatBinopExpr) {

            VarState op1State = this.inState
                    .getVarState((((AbstractJimpleFloatBinopExpr) rightOp)
                            .getOp1()));

            VarState op2State = this.inState
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
                rightOpState = op1State.rem(op2State);
            } else {
                assert false;
            }
        }
        this.inState.updateVarState(leftOp, rightOpState);
        fallOut.add(inState);
    };

}
