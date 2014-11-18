package filters;

import java.io.InvalidObjectException;

/**
 * MaxFilter
 * @author Adam Gleichsner
 * Extending scalar filter, we want the output to be the greatest value
 * without storing every input
 */
public class MaxFilter extends ScalarFilter {
	
	/**
	 * Constructor to initialize output to null
	 */
	public MaxFilter() {
		output = null;
	}

	/**
	 * Returns output is valid, throws if null
	 * @return Double - output
	 * @throws InvalidObjectException
	 */
	@Override
	public Double getOutput() throws InvalidObjectException {
		this.throwIfInvalidOutput();
		return output;
	}

	/**
	 * Grabs input and processes to set or keep output as the
	 * max value
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

	/**
	 * Determines if we need to switch our old output with a new value
	 * @param input
	 * @throws InvalidObjectException
	 */
	@Override
	protected Double calculateOutput(Double input) throws InvalidObjectException {
		this.throwIfNullInput(input);
		if (output == null || input > output)
			return input;
		return output;
	}

}
