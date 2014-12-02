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
	
	/**
	 * {@inheritDoc}
	 * @return Double - output
	 * @throws InvalidObjectException
	 */
	@Override
	public Double getOutput() throws InvalidObjectException {
		this.throwIfInvalidOutput();
		return output;
	}
	
	/**
	 * {@inheritDoc}
	 * @param input
	 * @throws InvalidObjectException
	 */
	@Override
	public void addInput(Double input) throws InvalidObjectException {
		this.throwIfNullInput(input);
		output = calculateOutput(input);
	}
	
	/**
	 * Resets output to the given reset value
	 * @param reset
	 * @throws InvalidObjectException
	 */
	@Override
	public void reset(Double reset) throws InvalidObjectException {
		this.throwIfNullInput(reset);
		output = reset;
	}
	
}
