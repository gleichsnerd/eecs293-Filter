package filters;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

/**
 * MinFilterN
 * @author Adam Gleichsner
 * Extends MinFilter, but now we only consider N values
 */
public class MinFilterN extends MinFilter {

	private int N;
	private List<Double> values;
	
	/**
	 * Constructor to initialize the size and list of values
	 * @param N - number of elements to record
	 */
	public MinFilterN(int N) {
		if (N <= 0)
			throw new IllegalArgumentException("Invalid buffer size");
		this.N = N;
		values = new ArrayList<Double>(N);
	}
	
	/**
	 * Override calculateOutput to handle a list with a max allowed
	 * values.
	 * @param input
	 * @throws InvalidObjectException
	 */
	@Override
	protected Double calculateOutput(Double input) throws InvalidObjectException {
		super.throwIfNullInput(input);
		
		//If we're not at out cap, just add
		if (values.size() < N)
			values.add(0, input);
		//Otherwise, pop the oldest element
		else {
			values.remove(N - 1);
			values.add(0, input);
		}
		
		return this.findSmallestValue();	
	}
	
	/**
	 * Overrides reset to clear the list and start over with the reset value
	 * @param reset
	 * @throws InvalidObjectException
	 */
	@Override
	public void reset (Double reset) throws InvalidObjectException {
		values = new ArrayList<Double>(N);
		values.add(reset);
		output = reset;
	}
	
	//Private methods
	
	/**
	 * Method to find the Smallest value in the values list
	 * @return Smallest value recorded
	 * @throws InvalidObjectException
	 */
	private Double findSmallestValue() throws InvalidObjectException {
		Double output = null;
		
		if (values.isEmpty())
			throw new InvalidObjectException("No valid inputs");
		
		for (Double value: values) {
			if (output == null || output > value)
				output = value;
		}
		
		return output;
	}
}
