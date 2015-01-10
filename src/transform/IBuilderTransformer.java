package transform;

import abstraction.LatticeElement;

public interface IBuilderTransformer {
	/**
	 * Build [-inf,this.high]
	 * @return
	 */
    LatticeElement createNegativeInfToHigh();
    /**
	 * Build [this.low,inf]
	 * @return
	 */
    LatticeElement createLowToPositiveInf();
}
