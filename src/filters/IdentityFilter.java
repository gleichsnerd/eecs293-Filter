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
public class IdentityFilter<T> implements Filter<T> {

	//Holds the output for calling
	private T output;
	
	/**
	 * Constructor to initialize the filter
	 */
	public IdentityFilter() {
		output = null;
	}

	/**
	 * Gets the output of the filter at the point of calling
	 * @return T - output of the filter
	 * @throws InvalidObjectException 
	 */
	@Override
	public T getOutput() throws InvalidObjectException {		
		this.throwIfInvalidOutput();
		return output;
	}

	/**
  	 * Inputs the new value and triggers the calculation of the
  	 * output
  	 * @param input
	 * @throws InvalidObjectException 
  	 */
	@Override
	public void addInput(T input) throws InvalidObjectException {
		this.throwIfNullInput(input);
		output = input;
	}

	/**
  	 * Resets the filter to a given value, erasing all previous
  	 * and unnecessary data
  	 * @param reset
	 * @throws InvalidObjectException 
  	 */
	@Override
	public void reset(T reset) throws InvalidObjectException {
		this.throwIfNullInput(reset);
		output = reset;	
	}

	/**
  	 * Method to call whenever giving the output in case something
  	 * is terribly wrong
  	 * @throws InvalidObjectException
  	 */
	@Override
	public void throwIfInvalidOutput() throws InvalidObjectException {
		if (output == null)
			throw new InvalidObjectException("Output is invalid");
	}

	/**
	 * Method called to make sure input is not null
	 * @param objects - collection of objects to check
	 * @throws InvalidObjectException
	 */
	@Override
	public void throwIfNullInput(Object...objects) throws InvalidObjectException {
		for (Object object:objects) {
			if (object == null)
				throw new InvalidObjectException("Input is invalid");
		}
	}

}
