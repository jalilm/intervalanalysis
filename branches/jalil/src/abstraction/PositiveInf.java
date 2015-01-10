package abstraction;

import soot.jimple.IntConstant;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public final class PositiveInf extends AbstractPositiveInf implements
        IMathTransformer, ILogicalTransformer {

    final IntConstant low;

    public PositiveInf(IntConstant low) {
        this.low = low;
    }
    
    public PositiveInf(int low) {
        this.low = IntConstant.v(low);
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
    public LatticeElement addInterval(Interval other) {
        return new PositiveInf(IntConstant.v(other.low.value + this.low.value));
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
        return new PositiveInf(IntConstant.v(other.low.value + this.low.value));
    }

    @Override
    public LatticeElement addNegativeInf(NegativeInf other) {
        return new Top();
    }

    @Override
    public LatticeElement subInterval(Interval other) {
        return new NegativeInf(IntConstant.v(other.high.value - this.low.value));
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
        return new Top();
    }

    @Override
    public LatticeElement subNegativeInf(NegativeInf other) {
        return new NegativeInf(IntConstant.v(other.high.value - this.low.value));
    }

    @Override
    public LatticeElement mulInterval(Interval other) {
        IntConstant lowest = IntConstant.v(Math.min(other.low.value
                * this.low.value, other.high.value * this.low.value));
        IntConstant highest = IntConstant.v(Math.max(other.low.value
                * this.low.value, other.high.value * this.low.value));
        if (other.low.value > 0) {
            return new PositiveInf(lowest);
        } else if (other.high.value < 0) {
            return new NegativeInf(highest);
        } else {
            return new Top();
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
        if (this.low.value < 0 || other.low.value < 0) {
            return new Top();
        } else {
            return new PositiveInf(IntConstant.v(this.low.value
                    * other.low.value));
        }
    }

    @Override
    public LatticeElement mulNegativeInf(NegativeInf other) {
        return new Top();
    }

    @Override
    public LatticeElement divInterval(Interval other) {
        if (this.low.value <= 0) {
            return new Top();
        }
        if (other.low.value > 0) {
            return new Interval(IntConstant.v(0),
                    IntConstant.v(other.high.value / this.low.value));
        } else if (other.high.value < 0) {
            return new Interval(
                    IntConstant.v(other.low.value / this.low.value),
                    IntConstant.v(0));
        } else {
            return new Interval(
                    IntConstant.v(other.low.value / this.low.value),
                    IntConstant.v(other.high.value / this.low.value));
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
        if (this.low.value <= 0) {
            return new Top();
        }
        if (other.low.value < 0) {
            return new PositiveInf(IntConstant.v(other.low.value
                    / this.low.value));
        } else {
            return new PositiveInf(IntConstant.v(0));
        }
    }

    @Override
    public LatticeElement divNegativeInf(NegativeInf other) {
        if (this.low.value <= 0) {
            return new Top();
        } else {
            /* We do not know what is -inf/+inf but it is negative. */
            return new NegativeInf(IntConstant.v(-1));
        }
    }

    @Override
    public LatticeElement modInterval(Interval other) {
        // TODO Jalil if you have time, tighten men.
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
        // TODO Jalil of you have time, tighten me.
        return new Top();
    }

    @Override
    public LatticeElement modNegativeInf(NegativeInf other) {
        // TODO Jalil of you have time, tighten me.
        return new Top();
    }

}
