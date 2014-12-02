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
	 * {@inheritDoc}
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
