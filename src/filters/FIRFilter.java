package filters;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

/**
 * FIRFilter
 * @author Adam Gleichsner
 * A FIR filter acts exactly the same as a scalar linear filter, except that all aValues are 0. So,
 * making things simple, we can initialize the super class with this new requirement when initializing 
 * this extended class.
 */
public class FIRFilter extends ScalarLinearFilter {

	/**
	 * Constructor for FIRFilter, doesn't need aValues or M
	 * @param bValues
	 * @param N
	 * @throws InvalidObjectException
	 */
	public FIRFilter(List<Double> bValues, int N) throws InvalidObjectException {
		super(new ArrayList<Double>(), bValues, 0, N);
	}

}
