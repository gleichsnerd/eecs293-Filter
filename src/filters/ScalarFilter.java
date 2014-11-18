package filters;

import java.io.InvalidObjectException;

/**
 * ScalarFilter
 * @author Adam Gleichsner
 * Typically, a scalar filter operates with doubles as values, so here we ditch
 * the generics and extend the Filter root interface with explicit type Double.
 * Unique to ScalarFilter is the introduction of a calculateOutput method, but
 * beyond that, ScalarFilter doesn't serve as an operating filter with an output. 
 */
public abstract class ScalarFilter extends Filter<Double> {
	
	/**
	 * Method to calculate output, whether it's max, min, or average
	 * @param input
	 * @return Double - output value to hold on to
	 */
	protected abstract Double calculateOutput(Double input) throws InvalidObjectException;
	
}
