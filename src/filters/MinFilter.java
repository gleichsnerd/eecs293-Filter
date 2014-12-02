package filters;

import java.io.InvalidObjectException;
/**
 * MinFilter
 * @author Adam Gleichsner
 * Extending scalar filter, we want the output to be the smallest value
 * without storing every input
 */
public class MinFilter extends ScalarFilter {

	/**
	 * Constructor to initialize output to null
	 */
	public MinFilter() {
		output = null;
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
	}

	/**
	 * {@inheritDoc}
	 * @param input
	 * @throws InvalidObjectException
	 */
	@Override
	protected Double calculateOutput(Double input) throws InvalidObjectException {
		this.throwIfNullInput(input);
		if (output == null || input < output)
			return input;
		return output;
	}

}
