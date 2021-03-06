package filters;

import java.io.InvalidObjectException;

/**
 * Filter interface
 * @author Adam Gleichsner
 * Filter is the base class which we'll implement as an abstract class. We do this since
 * we have a relatively singly chained  
 * define additional interfaces later on, and so we can also implement the generic type, 
 * input/output stubs for all more specific filters, and the reset option.
 *
 * @param <T>
 */
public abstract class Filter<T> {
	
	protected T output;
		 
	//Define basic methods every Filter should have
	
	/**
	 * Gets the output of the filter at the point of calling
	 * @return T - output of the filter
	 * @throws InvalidObjectException 
	 */
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
  	public abstract void addInput (T input) throws InvalidObjectException;

  	/**
  	 * Resets the filter to a given value, erasing all previous
  	 * and unnecessary data
  	 * @param reset
  	 * @throws InvalidObjectException 
  	 */
  	public abstract void reset(T reset) throws InvalidObjectException;
  	
  	/**
  	 * Method to call whenever giving the output in case something
  	 * is terribly wrong
  	 * @throws InvalidObjectException
  	 */
  	protected void throwIfInvalidOutput() throws InvalidObjectException {
  		if (output == null)
			throw new InvalidObjectException("Output is invalid");
  	}

  	/**
  	 * Method called to make sure no null input is given
  	 * @throws InvalidObjectException
  	 */
  	protected void throwIfNullInput(Object...objects) throws InvalidObjectException {
  		for (Object object: objects) {
			if (object == null)
				throw new InvalidObjectException("Input is invalid");
		}
  	}
}
