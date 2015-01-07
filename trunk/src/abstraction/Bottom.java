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
    public void addInterval(Interval other, LatticeElement result) {
        result = other;
    }

    @Override
    public void addTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void addBottom(Bottom other, LatticeElement result) {
        result = this;
    }

    @Override
    public void addPositiveInf(PositiveInf other, LatticeElement result) {
        result = other;
    }

    @Override
    public void addNegativeInf(NegativeInf other, LatticeElement result) {
        result = other;
    }

    @Override
    public void subInterval(Interval other, LatticeElement result) {
        result = other;
    }

    @Override
    public void subTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void subBottom(Bottom other, LatticeElement result) {
        result = this;
    }

    @Override
    public void subPositiveInf(PositiveInf other, LatticeElement result) {
        result = other;
    }

    @Override
    public void subNegativeInf(NegativeInf other, LatticeElement result) {
        result = other;
    }

    @Override
    public void mulInterval(Interval other, LatticeElement result) {
        result = other;
    }

    @Override
    public void mulTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void mulBottom(Bottom other, LatticeElement result) {
        result = this;
    }

    @Override
    public void mulPositiveInf(PositiveInf other, LatticeElement result) {
        result = other;
    }

    @Override
    public void mulNegativeInf(NegativeInf other, LatticeElement result) {
        result = other;
    }

    @Override
    public void divInterval(Interval other, LatticeElement result) {
        result = other;
    }

    @Override
    public void divTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void divBottom(Bottom other, LatticeElement result) {
        result = this;
    }

    @Override
    public void divPositiveInf(PositiveInf other, LatticeElement result) {
        result = other;
    }

    @Override
    public void divNegativeInf(NegativeInf other, LatticeElement result) {
        result = other;
    }

    @Override
    public void modInterval(Interval other, LatticeElement result) {
        result = other;
    }

    @Override
    public void modTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void modBottom(Bottom other, LatticeElement result) {
        result = this;
    }

    @Override
    public void modPositiveInf(PositiveInf other, LatticeElement result) {
        result = other;
    }

    @Override
    public void modNegativeInf(NegativeInf other, LatticeElement result) {
        result = other;
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
