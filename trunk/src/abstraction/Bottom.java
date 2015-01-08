package abstraction;

import transform.ILogicalTransformer;
import transform.IMathTransformer;

public class Bottom extends AbstractBottom implements IMathTransformer,
        ILogicalTransformer {

    @Override
    public String toString() {
        return "Bottom";
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
        return other;
    }

    @Override
    public LatticeElement addTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement addBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement addPositiveInf(PositiveInf other) {
        return other;
    }

    @Override
    public LatticeElement addNegativeInf(NegativeInf other) {
        return other;
    }

    @Override
    public LatticeElement subInterval(Interval other) {
        return other;
    }

    @Override
    public LatticeElement subTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement subBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement subPositiveInf(PositiveInf other) {
        return other;
    }

    @Override
    public LatticeElement subNegativeInf(NegativeInf other) {
        return other;
    }

    @Override
    public LatticeElement mulInterval(Interval other) {
        return other;
    }

    @Override
    public LatticeElement mulTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement mulBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement mulPositiveInf(PositiveInf other) {
        return other;
    }

    @Override
    public LatticeElement mulNegativeInf(NegativeInf other) {
        return other;
    }

    @Override
    public LatticeElement divInterval(Interval other) {
        return other;
    }

    @Override
    public LatticeElement divTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement divBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement divPositiveInf(PositiveInf other) {
        return other;
    }

    @Override
    public LatticeElement divNegativeInf(NegativeInf other) {
        return other;
    }

    @Override
    public LatticeElement modInterval(Interval other) {
        return other;
    }

    @Override
    public LatticeElement modTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement modBottom(Bottom other) {
        return this;
    }

    @Override
    public LatticeElement modPositiveInf(PositiveInf other) {
        return other;
    }

    @Override
    public LatticeElement modNegativeInf(NegativeInf other) {
        return other;
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
