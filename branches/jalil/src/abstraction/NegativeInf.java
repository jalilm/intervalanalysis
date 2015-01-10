package abstraction;

import soot.jimple.IntConstant;
import transform.ILogicalTransformer;
import transform.IMathTransformer;

public final class NegativeInf extends AbstractNegativeInf implements
        IMathTransformer, ILogicalTransformer {

    final IntConstant high;

    public NegativeInf(IntConstant high) {
        this.high = high;
    }
    
    public NegativeInf(int high) {
        this.high = IntConstant.v(high);
    }

    @Override
    public String toString() {
        return "[-inf," + high.value + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NegativeInf other = (NegativeInf) obj;
        if (!this.high.equals(other.high)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.high.hashCode();
        return hash;
    }

    @Override
    public LatticeElement addInterval(Interval other) {
        return new NegativeInf(
                IntConstant.v(other.high.value + this.high.value));
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
        return new Top();
    }

    @Override
    public LatticeElement addNegativeInf(NegativeInf other) {
        return new NegativeInf(
                IntConstant.v(other.high.value + this.high.value));
    }

    @Override
    public LatticeElement subInterval(Interval other) {
        return new PositiveInf(IntConstant.v(other.low.value - this.high.value));
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
        return new PositiveInf(IntConstant.v(other.low.value - this.high.value));
    }

    @Override
    public LatticeElement subNegativeInf(NegativeInf other) {
        return new Top();
    }

    @Override
    public LatticeElement mulInterval(Interval other) {
        IntConstant lowest = IntConstant.v(Math.min(other.low.value
                * this.high.value, other.high.value * this.high.value));
        IntConstant highest = IntConstant.v(Math.max(other.low.value
                * this.high.value, other.high.value * this.high.value));
        if (other.low.value > 0) {
            return new NegativeInf(highest);
        } else if (other.high.value < 0) {
            return new PositiveInf(lowest);
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
        return new Top();
    }

    @Override
    public LatticeElement mulNegativeInf(NegativeInf other) {
        if (this.high.value > 0 || other.high.value > 0) {
            return new Top();
        } else {
            return new PositiveInf(IntConstant.v(this.high.value
                    * other.high.value));
        }
    }

    @Override
    public LatticeElement divInterval(Interval other) {
        if (this.high.value >= 0) {
            return new Top();
        }
        if (other.low.value > 0) {
            return new Interval(IntConstant.v(other.high.value
                    / this.high.value), IntConstant.v(0));
        } else if (other.high.value < 0) {
            return new Interval(IntConstant.v(0), IntConstant.v(other.low.value
                    / this.high.value));
        } else {
            return new Interval(IntConstant.v(other.high.value
                    / this.high.value), IntConstant.v(other.low.value
                    / this.high.value));
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
        if (this.high.value > 0) {
            return new Top();
        } else {
            /* We do not know what is +inf/-inf but it is negative. */
            return new NegativeInf(IntConstant.v(-1));
        }

    }

    @Override
    public LatticeElement divNegativeInf(NegativeInf other) {
        if (this.high.value > 0) {
            return new Top();
        } else {
            if (other.high.value < 0) {
                return new PositiveInf(IntConstant.v(1));
            } else {
                return new PositiveInf(IntConstant.v(other.high.value
                        / this.high.value));
            }
        }
    }

    @Override
    public LatticeElement modInterval(Interval other) {
        // TODO Jalil if you have time, tighten me.
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


}
