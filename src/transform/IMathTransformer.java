package transform;

import abstraction.Bottom;
import abstraction.Interval;
import abstraction.LatticeElement;
import abstraction.NegativeInf;
import abstraction.PositiveInf;
import abstraction.Top;

public interface IMathTransformer {
    void addInterval(Interval other, LatticeElement result);

    void addTop(Top other, LatticeElement result);

    void addBottom(Bottom other, LatticeElement result);

    void addPositiveInf(PositiveInf other, LatticeElement result);

    void addNegativeInf(NegativeInf other, LatticeElement result);

    void subInterval(Interval other, LatticeElement result);

    void subTop(Top other, LatticeElement result);

    void subBottom(Bottom other, LatticeElement result);

    void subPositiveInf(PositiveInf other, LatticeElement result);

    void subNegativeInf(NegativeInf other, LatticeElement result);

    void mulInterval(Interval other, LatticeElement result);

    void mulTop(Top other, LatticeElement result);

    void mulBottom(Bottom other, LatticeElement result);

    void mulPositiveInf(PositiveInf other, LatticeElement result);

    void mulNegativeInf(NegativeInf other, LatticeElement result);

    void divInterval(Interval other, LatticeElement result);

    void divTop(Top other, LatticeElement result);

    void divBottom(Bottom other, LatticeElement result);

    void divPositiveInf(PositiveInf other, LatticeElement result);

    void divNegativeInf(NegativeInf other, LatticeElement result);

    void modInterval(Interval other, LatticeElement result);

    void modTop(Top other, LatticeElement result);

    void modBottom(Bottom other, LatticeElement result);

    void modPositiveInf(PositiveInf other, LatticeElement result);

    void modNegativeInf(NegativeInf other, LatticeElement result);

}
