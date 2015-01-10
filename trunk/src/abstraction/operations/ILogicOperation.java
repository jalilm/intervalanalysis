package abstraction.operations;

import intervalAnalysis.State;

import soot.Value;



public interface ILogicOperation {
	/**
	 * Return State for True path
	 */
	State op(State in, Value left, Value right);

	/**
	 * Return State for False path
	 */
	State negate(State in,Value left, Value right);
}
