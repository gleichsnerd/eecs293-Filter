package filters;

import java.io.InvalidObjectException;

/**
 * AveragingFilter
 * @author Adam Gleichsner
 * Extending scalar filter, we want the output to be the average value
 * without storing every input
 */
public class AveragingFilter extends ScalarFilter {
	
	//Keep track of past average and number of elements in said average
	private Double average;
	private int items;
	
	/**
	 * Constructor to initialize output to null
	 */
	public AveragingFilter() {
		output = null;
		average = null;
		items = 0;
	}

	/**
	 * {@inheritDoc}
	 * @param reset
	 * @throws InvalidObjectException
	 */
	@Override
	public void reset(Double reset) throws InvalidObjectException {
		this.throwIfNullInput(reset);
		output = reset;
		average = reset;
		items = 1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void throwIfInvalidOutput() throws InvalidObjectException {
		if (output == null || items == 0 || average == null)
			throw new InvalidObjectException("Output is invalid");
	}

	/**
	 * {@inheritDoc}
	 * @param input
	 * @throws InvalidObjectException
	 */
	@Override
	protected Double calculateOutput(Double input) throws InvalidObjectException {
		this.throwIfNullInput(input);
		if (output == null || average == null) {
			items = 1;
			average = input;
		} else {
			items++;
			average = average + (input - average) / items;
		}
		
		return average;
		
	}

}
