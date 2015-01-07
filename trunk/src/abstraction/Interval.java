package abstraction;

import soot.jimple.IntConstant;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public class Interval extends AbstractInterval implements ILogicalTransformer,
        IMathTransformer {

    final IntConstant low;
    final IntConstant high;

    public Interval(IntConstant low, IntConstant high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public String toString() {
        return "[" + low.value + "," + high.value + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Interval other = (Interval) obj;
        if (!this.low.equals(other.low)) {
            return false;
        }
        if (!this.high.equals(other.high)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.low.hashCode();
        hash = 53 * hash + this.high.hashCode();
        return hash;
    }

    @Override
    public void addInterval(Interval other, LatticeElement result) {
        result = new Interval(IntConstant.v(this.low.value + other.low.value),
                IntConstant.v(this.high.value + other.high.value));
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
                IntConstant.v(this.low.value + other.low.value));

    }

    @Override
    public void addNegativeInf(NegativeInf other, LatticeElement result) {
        result = new NegativeInf(IntConstant.v(other.high.value
                + this.high.value));
    }

    @Override
    public void subInterval(Interval other, LatticeElement result) {
        result = new Interval(IntConstant.v(other.low.value - this.high.value),
                IntConstant.v(other.high.value + this.low.value));
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
        result = new PositiveInf(IntConstant.v(other.low.value
                - this.high.value));
    }

    @Override
    public void subNegativeInf(NegativeInf other, LatticeElement result) {
        result = new NegativeInf(IntConstant.v(other.high.value
                - this.low.value));
    }

    @Override
    public void mulInterval(Interval other, LatticeElement result) {
        if (this.low.value >= 0) {
            if (other.low.value >= 0) {
                result = new Interval(IntConstant.v(this.low.value
                        * other.low.value), IntConstant.v(this.high.value
                        * other.high.value));
            } else {
                if (other.high.value <= 0) {
                    result = new Interval(IntConstant.v(this.high.value
                            * other.low.value), IntConstant.v(this.low.value
                            * other.high.value));
                } else {
                    result = new Interval(IntConstant.v(this.high.value
                            * other.low.value), IntConstant.v(this.high.value
                            * other.high.value));
                }
            }
        } else {
            if (this.high.value >= 0) {
                if (other.low.value >= 0) {
                    result = new Interval(IntConstant.v(this.low.value
                            * other.high.value), IntConstant.v(this.high.value
                            * other.high.value));
                } else { // other.low.value < 0
                    if (other.high.value < 0) {
                        result = new Interval(IntConstant.v(this.high.value
                                * other.low.value),
                                IntConstant.v(this.low.value * other.low.value));
                    } else { // other.low.value < 0 && other.high.value >= 0
                        result = new Interval(IntConstant.v(Math.min(
                                this.low.value * other.high.value,
                                this.high.value * other.low.value)),
                                IntConstant.v(Math.max(this.low.value
                                        * other.low.value, this.high.value
                                        * other.high.value)));
                    }
                }
            } else { // this.high.value < 0
                if (other.low.value >= 0) {
                    result = new Interval(IntConstant.v(this.low.value
                            * other.high.value), IntConstant.v(this.high.value
                            * other.high.value));
                } else { // other.low.value < 0
                    if (other.high.value < 0) {
                        result = new Interval(IntConstant.v(this.high.value
                                * other.high.value),
                                IntConstant.v(this.low.value * other.low.value));
                    } else { // other.low.value < 0 && other.high.value >= 0
                        result = new Interval(IntConstant.v(this.low.value
                                * other.high.value),
                                IntConstant.v(this.low.value * other.low.value));
                    }
                }
            }
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
        IntConstant lowest = IntConstant.v(Math.min(this.low.value
                * other.low.value, this.high.value * other.low.value));
        IntConstant highest = IntConstant.v(Math.max(this.low.value
                * other.low.value, this.high.value * other.low.value));
        if (this.low.value > 0) {
            result = new PositiveInf(lowest);
        } else if (this.high.value < 0) {
            result = new NegativeInf(highest);
        } else {
            result = new Top();
        }
    }

    @Override
    public void mulNegativeInf(NegativeInf other, LatticeElement result) {
        IntConstant lowest = IntConstant.v(Math.min(this.low.value
                * other.high.value, this.high.value * other.high.value));
        IntConstant highest = IntConstant.v(Math.max(this.low.value
                * other.high.value, this.high.value * other.high.value));
        if (this.low.value <= 0 && this.high.value >= 0) {
            result = new Top();
            return;
        }
        if (this.high.value < 0) {
            result = new PositiveInf(lowest);
        } else { // this.low.value > 0
            result = new NegativeInf(highest);
        }
    }

    @Override
    public void divInterval(Interval other, LatticeElement result) {
        if (this.low.value <= 0 && this.high.value >= 0) {
            result = new Top();
            return;
        }
        if (this.low.value > 0) {
            if (other.low.value > 0) {
                result = new Interval(IntConstant.v(other.low.value
                        / this.high.value), IntConstant.v(other.high.value
                        / this.low.value));
            } else {
                if (other.high.value < 0) {
                    result = new Interval(IntConstant.v(other.low.value
                            / this.low.value), IntConstant.v(other.high.value
                            / this.high.value));
                } else {
                    result = new Interval(IntConstant.v(other.low.value
                            / this.low.value), IntConstant.v(other.low.value
                            / this.high.value));
                }
            }
        } else { // this.high.value < 0
            if (other.low.value > 0) {
                result = new Interval(IntConstant.v(other.high.value
                        / this.high.value), IntConstant.v(other.low.value
                        / this.low.value));
            } else {
                if (other.high.value < 0) {
                    result = new Interval(IntConstant.v(other.low.value
                            / this.high.value), IntConstant.v(other.high.value
                            / this.low.value));
                } else {// other.high.value > 0
                    result = new Interval(IntConstant.v(other.high.value
                            / this.high.value), IntConstant.v(other.low.value
                            / this.high.value));
                }
            }
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
        IntConstant lowest = IntConstant.v(Math.min(other.low.value
                / this.high.value, other.low.value / this.low.value));
        IntConstant highest = IntConstant.v(Math.max(other.low.value
                / this.high.value, other.low.value / this.low.value));
        if (this.low.value <= 0 && this.high.value >= 0) {
            result = new Top();
            return;
        }
        if (this.low.value > 0) {
            result = new PositiveInf(lowest);
        } else { // this.high.value < 0
            result = new NegativeInf(highest);
        }
    }

    @Override
    public void divNegativeInf(NegativeInf other, LatticeElement result) {
        IntConstant lowest = IntConstant.v(Math.min(other.high.value
                / this.high.value, other.high.value / this.low.value));
        IntConstant highest = IntConstant.v(Math.max(other.high.value
                / this.high.value, other.high.value / this.low.value));
        if (this.low.value <= 0 && this.high.value >= 0) {
            result = new Top();
            return;
        }
        if (this.low.value > 0) {
            result = new NegativeInf(highest);
        } else { // this.high.value < 0
            result = new PositiveInf(lowest);
        }
    }

    @Override
    public void modInterval(Interval other, LatticeElement result) {
        // TODO Jalil If you have time, tighten me.
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
        // TODO Jalil if you have time, tighten me.
        result = new Top();
    }

    @Override
    public void modNegativeInf(NegativeInf other, LatticeElement result) {
        // TODO Jalil if you have time, tighten me.
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
