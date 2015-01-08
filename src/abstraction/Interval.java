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
    public LatticeElement addInterval(Interval other) {
        return new Interval(IntConstant.v(this.low.value + other.low.value),
                IntConstant.v(this.high.value + other.high.value));
    }

    @Override
    public LatticeElement addTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement addBottom(Bottom other) {
        return other;
    }

    @Override
    public LatticeElement addPositiveInf(PositiveInf other) {
        return new PositiveInf(
                IntConstant.v(this.low.value + other.low.value));

    }

    @Override
    public LatticeElement addNegativeInf(NegativeInf other) {
        return new NegativeInf(IntConstant.v(other.high.value
                + this.high.value));
    }

    @Override
    public LatticeElement subInterval(Interval other) {
        return new Interval(IntConstant.v(other.low.value - this.high.value),
                IntConstant.v(other.high.value - this.low.value));
    }

    @Override
    public LatticeElement subTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement subBottom(Bottom other) {
        return other;
    }

    @Override
    public LatticeElement subPositiveInf(PositiveInf other) {
        return new PositiveInf(IntConstant.v(other.low.value
                - this.high.value));
    }

    @Override
    public LatticeElement subNegativeInf(NegativeInf other) {
        return new NegativeInf(IntConstant.v(other.high.value
                - this.low.value));
    }

    @Override
    public LatticeElement mulInterval(Interval other) {
        if (this.low.value >= 0) {
            if (other.low.value >= 0) {
                return new Interval(IntConstant.v(this.low.value
                        * other.low.value), IntConstant.v(this.high.value
                        * other.high.value));
            } else {
                if (other.high.value <= 0) {
                    return new Interval(IntConstant.v(this.high.value
                            * other.low.value), IntConstant.v(this.low.value
                            * other.high.value));
                } else {
                    return new Interval(IntConstant.v(this.high.value
                            * other.low.value), IntConstant.v(this.high.value
                            * other.high.value));
                }
            }
        } else {
            if (this.high.value >= 0) {
                if (other.low.value >= 0) {
                    return new Interval(IntConstant.v(this.low.value
                            * other.high.value), IntConstant.v(this.high.value
                            * other.high.value));
                } else { // other.low.value < 0
                    if (other.high.value < 0) {
                        return new Interval(IntConstant.v(this.high.value
                                * other.low.value),
                                IntConstant.v(this.low.value * other.low.value));
                    } else { // other.low.value < 0 && other.high.value >= 0
                        return new Interval(IntConstant.v(Math.min(
                                this.low.value * other.high.value,
                                this.high.value * other.low.value)),
                                IntConstant.v(Math.max(this.low.value
                                        * other.low.value, this.high.value
                                        * other.high.value)));
                    }
                }
            } else { // this.high.value < 0
                if (other.low.value >= 0) {
                    return new Interval(IntConstant.v(this.low.value
                            * other.high.value), IntConstant.v(this.high.value
                            * other.high.value));
                } else { // other.low.value < 0
                    if (other.high.value < 0) {
                        return new Interval(IntConstant.v(this.high.value
                                * other.high.value),
                                IntConstant.v(this.low.value * other.low.value));
                    } else { // other.low.value < 0 && other.high.value >= 0
                        return new Interval(IntConstant.v(this.low.value
                                * other.high.value),
                                IntConstant.v(this.low.value * other.low.value));
                    }
                }
            }
        }
    }

    @Override
    public LatticeElement mulTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement mulBottom(Bottom other) {
        return other;
    }

    @Override
    public LatticeElement mulPositiveInf(PositiveInf other) {
        IntConstant lowest = IntConstant.v(Math.min(this.low.value
                * other.low.value, this.high.value * other.low.value));
        IntConstant highest = IntConstant.v(Math.max(this.low.value
                * other.low.value, this.high.value * other.low.value));
        if (this.low.value > 0) {
            return new PositiveInf(lowest);
        } else if (this.high.value < 0) {
            return new NegativeInf(highest);
        } else {
            return new Top();
        }
    }

    @Override
    public LatticeElement mulNegativeInf(NegativeInf other) {
        IntConstant lowest = IntConstant.v(Math.min(this.low.value
                * other.high.value, this.high.value * other.high.value));
        IntConstant highest = IntConstant.v(Math.max(this.low.value
                * other.high.value, this.high.value * other.high.value));
        if (this.low.value <= 0 && this.high.value >= 0) {
            return new Top();
        }
        if (this.high.value < 0) {
            return new PositiveInf(lowest);
        } else { // this.low.value > 0
            return new NegativeInf(highest);
        }
    }

    @Override
    public LatticeElement divInterval(Interval other) {
        if (this.low.value <= 0 && this.high.value >= 0) {
            return new Top();
        }
        if (this.low.value > 0) {
            if (other.low.value > 0) {
                return new Interval(IntConstant.v(other.low.value
                        / this.high.value), IntConstant.v(other.high.value
                        / this.low.value));
            } else {
                if (other.high.value < 0) {
                    return new Interval(IntConstant.v(other.low.value
                            / this.low.value), IntConstant.v(other.high.value
                            / this.high.value));
                } else {
                    return new Interval(IntConstant.v(other.low.value
                            / this.low.value), IntConstant.v(other.low.value
                            / this.high.value));
                }
            }
        } else { // this.high.value < 0
            if (other.low.value > 0) {
                return new Interval(IntConstant.v(other.high.value
                        / this.high.value), IntConstant.v(other.low.value
                        / this.low.value));
            } else {
                if (other.high.value < 0) {
                    return new Interval(IntConstant.v(other.low.value
                            / this.high.value), IntConstant.v(other.high.value
                            / this.low.value));
                } else {// other.high.value > 0
                    return new Interval(IntConstant.v(other.high.value
                            / this.high.value), IntConstant.v(other.low.value
                            / this.high.value));
                }
            }
        }
    }

    @Override
    public LatticeElement divTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement divBottom(Bottom other) {
        return other;
    }

    @Override
    public LatticeElement divPositiveInf(PositiveInf other) {
        IntConstant lowest = IntConstant.v(Math.min(other.low.value
                / this.high.value, other.low.value / this.low.value));
        IntConstant highest = IntConstant.v(Math.max(other.low.value
                / this.high.value, other.low.value / this.low.value));
        if (this.low.value <= 0 && this.high.value >= 0) {
            return new Top();
        }
        if (this.low.value > 0) {
            return new PositiveInf(lowest);
        } else { // this.high.value < 0
            return new NegativeInf(highest);
        }
    }

    @Override
    public LatticeElement divNegativeInf(NegativeInf other) {
        IntConstant lowest = IntConstant.v(Math.min(other.high.value
                / this.high.value, other.high.value / this.low.value));
        IntConstant highest = IntConstant.v(Math.max(other.high.value
                / this.high.value, other.high.value / this.low.value));
        if (this.low.value <= 0 && this.high.value >= 0) {
            return new Top();
        }
        if (this.low.value > 0) {
            return new NegativeInf(highest);
        } else { // this.high.value < 0
            return new PositiveInf(lowest);
        }
    }

    @Override
    public LatticeElement modInterval(Interval other) {
        // TODO Jalil If you have time, tighten me.
        return new Top();
    }

    @Override
    public LatticeElement modTop(Top other) {
        return other;
    }

    @Override
    public LatticeElement modBottom(Bottom other) {
        return other;
    }

    @Override
    public LatticeElement modPositiveInf(PositiveInf other) {
        // TODO Jalil if you have time, tighten me.
        return new Top();
    }

    @Override
    public LatticeElement modNegativeInf(NegativeInf other) {
        // TODO Jalil if you have time, tighten me.
        return new Top();
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
