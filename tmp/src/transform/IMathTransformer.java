package transform;

import abstraction.Bottom;
import abstraction.Interval;
import abstraction.LatticeElement;
import abstraction.NegativeInf;
import abstraction.PositiveInf;
import abstraction.Top;

public interface IMathTransformer {
    LatticeElement addInterval(Interval other);

    LatticeElement addTop(Top other);

    LatticeElement addBottom(Bottom other);

    LatticeElement addPositiveInf(PositiveInf other);

    LatticeElement addNegativeInf(NegativeInf other);

    LatticeElement subInterval(Interval other);

    LatticeElement subTop(Top other);

    LatticeElement subBottom(Bottom other);

    LatticeElement subPositiveInf(PositiveInf other);

    LatticeElement subNegativeInf(NegativeInf other);

    LatticeElement mulInterval(Interval other);

    LatticeElement mulTop(Top other);

    LatticeElement mulBottom(Bottom other);

    LatticeElement mulPositiveInf(PositiveInf other);

    LatticeElement mulNegativeInf(NegativeInf other);

    LatticeElement divInterval(Interval other);

    LatticeElement divTop(Top other);

    LatticeElement divBottom(Bottom other);

    LatticeElement divPositiveInf(PositiveInf other);

    LatticeElement divNegativeInf(NegativeInf other);

    LatticeElement modInterval(Interval other);

    LatticeElement modTop(Top other);

    LatticeElement modBottom(Bottom other);

    LatticeElement modPositiveInf(PositiveInf other);

    LatticeElement modNegativeInf(NegativeInf other);

}
