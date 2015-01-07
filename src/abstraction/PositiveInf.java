package abstraction;

import soot.jimple.IntConstant;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public class PositiveInf extends AbstractPositiveInf implements
        IMathTransformer, ILogicalTransformer {

    final IntConstant low;

    public PositiveInf(IntConstant low) {
        this.low = low;
    }

    @Override
    public String toString() {
        return "[" + low.value + ",+inf]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PositiveInf other = (PositiveInf) obj;
        if (!this.low.equals(other.low)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.low.hashCode();
        return hash;
    }

    @Override
    public void addInterval(Interval other, LatticeElement result) {
        result = new PositiveInf(
                IntConstant.v(other.low.value + this.low.value));
    }

    @Override
    public void addTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void addBottom(Bottom other, LatticeElement result) {
        result = other;
    }

    @Override
    public void addPositiveInf(PositiveInf other, LatticeElement result) {
        result = new PositiveInf(
                IntConstant.v(other.low.value + this.low.value));
    }

    @Override
    public void addNegativeInf(NegativeInf other, LatticeElement result) {
        result = new Top();
    }

    @Override
    public void subInterval(Interval other, LatticeElement result) {
        result = new NegativeInf(IntConstant.v(other.high.value
                - this.low.value));
    }

    @Override
    public void subTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void subBottom(Bottom other, LatticeElement result) {
        result = other;

    }

    @Override
    public void subPositiveInf(PositiveInf other, LatticeElement result) {
        result = new Top();
    }

    @Override
    public void subNegativeInf(NegativeInf other, LatticeElement result) {
        result = new NegativeInf(IntConstant.v(other.high.value
                - this.low.value));
    }

    @Override
    public void mulInterval(Interval other, LatticeElement result) {
        IntConstant lowest = IntConstant.v(Math.min(other.low.value
                * this.low.value, other.high.value * this.low.value));
        IntConstant highest = IntConstant.v(Math.max(other.low.value
                * this.low.value, other.high.value * this.low.value));
        if (other.low.value > 0) {
            result = new PositiveInf(lowest);
        } else if (other.high.value < 0) {
            result = new NegativeInf(highest);
        } else {
            result = new Top();
        }
    }

    @Override
    public void mulTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void mulBottom(Bottom other, LatticeElement result) {
        result = other;
    }

    @Override
    public void mulPositiveInf(PositiveInf other, LatticeElement result) {
        if (this.low.value < 0 || other.low.value < 0) {
            result = new Top();
        } else {
            result = new PositiveInf(IntConstant.v(this.low.value
                    * other.low.value));
        }
    }

    @Override
    public void mulNegativeInf(NegativeInf other, LatticeElement result) {
        result = new Top();
    }

    @Override
    public void divInterval(Interval other, LatticeElement result) {
        if (this.low.value <= 0) {
            result = new Top();
            return;
        }
        if (other.low.value > 0) {
            result = new Interval(IntConstant.v(0),
                    IntConstant.v(other.high.value / this.low.value));
        } else if (other.high.value < 0) {
            result = new Interval(IntConstant.v(other.low.value
                    / this.low.value), IntConstant.v(0));
        } else {
            result = new Interval(IntConstant.v(other.low.value
                    / this.low.value), IntConstant.v(other.high.value
                    / this.low.value));
        }
    }

    @Override
    public void divTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void divBottom(Bottom other, LatticeElement result) {
        result = other;
    }

    @Override
    public void divPositiveInf(PositiveInf other, LatticeElement result) {
        if (this.low.value <= 0) {
            result = new Top();
            return;
        }
        if (other.low.value < 0) {
            result = new PositiveInf(IntConstant.v(other.low.value
                    / this.low.value));
        } else {
            result = new PositiveInf(IntConstant.v(0));
        }
    }

    @Override
    public void divNegativeInf(NegativeInf other, LatticeElement result) {
        if (this.low.value <= 0) {
            result = new Top();
        } else {
            /* We do not know what is -inf/+inf but it is negative. */
            result = new NegativeInf(IntConstant.v(-1));
        }
    }

    @Override
    public void modInterval(Interval other, LatticeElement result) {
        // TODO Jalil if you have time, tighten men.
        result = new Top();
    }

    @Override
    public void modTop(Top other, LatticeElement result) {
        result = other;
    }

    @Override
    public void modBottom(Bottom other, LatticeElement result) {
        result = other;
    }

    @Override
    public void modPositiveInf(PositiveInf other, LatticeElement result) {
        // TODO Jalil of you have time, tighten me.
        result = new Top();
    }

    @Override
    public void modNegativeInf(NegativeInf other, LatticeElement result) {
        // TODO Jalil of you have time, tighten me.
        result = new Top();
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
