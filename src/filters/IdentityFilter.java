package filters;

import java.io.InvalidObjectException;

/**
 * IdentityFilter
 * @author Adam Gleichsner
 * The IdentityFilter simply sets the output to the input. Because of how lenient
 * it is with regards to type, we define it directly as a filter outside of the 
 * scalar family.
 * @param <T>
 */
public class IdentityFilter<T> extends Filter<T> {
	
	/**
	 * Constructor to initialize the filter
	 */
	public IdentityFilter() {
		output = null;
	}

	/**
  	 * {@inheritDoc}
  	 * @param input
	 * @throws InvalidObjectException 
  	 */
	@Override
	public void addInput(T input) throws InvalidObjectException {
		this.throwIfNullInput(input);
		output = input;
	}

	/**
  	 * {@inheritDoc}
  	 * @param reset
	 * @throws InvalidObjectException 
  	 */
	@Override
	public void reset(T reset) throws InvalidObjectException {
		this.throwIfNullInput(reset);
		output = reset;	
	}
}
