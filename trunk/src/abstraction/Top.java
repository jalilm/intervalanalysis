package abstraction;

import transform.ILogicalTransformer;
import transform.IMathTransformer;

public class Top extends TopAbstract implements IMathTransformer,
        ILogicalTransformer {

    @Override
    public String toString() {
        return "[-inf,+inf]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public LatticeElement addInterval(Interval other) {
        return this;
    }

    @Override
    public LatticeElement addTop(Top other) {
        return this;
    }

    @Override
    public LatticeElement addBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement addPositiveInf(PositiveInf other) {
        return this;
    }

    @Override
    public LatticeElement addNegativeInf(NegativeInf other) {
        return this;
    }

    @Override
    public LatticeElement subInterval(Interval other) {
        return this;
    }

    @Override
    public LatticeElement subTop(Top other) {
        return this;
    }

    @Override
    public LatticeElement subBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement subPositiveInf(PositiveInf other) {
        return this;
    }

    @Override
    public LatticeElement subNegativeInf(NegativeInf other) {
        return this;
    }

    @Override
    public LatticeElement mulInterval(Interval other) {
        return this;
    }

    @Override
    public LatticeElement mulTop(Top other) {
        return this;
    }

    @Override
    public LatticeElement mulBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement mulPositiveInf(PositiveInf other) {
        return this;
    }

    @Override
    public LatticeElement mulNegativeInf(NegativeInf other) {
        return this;
    }

    @Override
    public LatticeElement divInterval(Interval other) {
        return this;
    }

    @Override
    public LatticeElement divTop(Top other) {
        return this;
    }

    @Override
    public LatticeElement divBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement divPositiveInf(PositiveInf other) {
        return this;
    }

    @Override
    public LatticeElement divNegativeInf(NegativeInf other) {
        return this;
    }

    @Override
    public LatticeElement modInterval(Interval other) {
        return this;
    }

    @Override
    public LatticeElement modTop(Top other) {
        return this;
    }

    @Override
    public LatticeElement modBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement modPositiveInf(PositiveInf other) {
        return this;
    }

    @Override
    public LatticeElement modNegativeInf(NegativeInf other) {
        return this;
    }

    @Override
    public void IfEq(LatticeElement other, LatticeElement trueResult,
            LatticeElement falseResult) {
        // TODO Auto-generated method stub

    }

    @Override
    public void IfNeq(LatticeElement other, LatticeElement trueResult,
            LatticeElement falseResult) {
        // TODO Auto-generated method stub

    }

    @Override
    public void IfGt(LatticeElement other, LatticeElement trueResult,
            LatticeElement falseResult) {
        // TODO Auto-generated method stub

    }

    @Override
    public void IfGe(LatticeElement other, LatticeElement trueResult,
            LatticeElement falseResult) {
        // TODO Auto-generated method stub

    }

    @Override
    public void IfLt(LatticeElement other, LatticeElement trueResult,
            LatticeElement falseResult) {
        // TODO Auto-generated method stub

    }

    @Override
    public void IfLe(LatticeElement other, LatticeElement trueResult,
            LatticeElement falseResult) {
        // TODO Auto-generated method stub

    }

}
