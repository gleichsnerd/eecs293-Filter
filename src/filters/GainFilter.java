package filters;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GainFilter
 * @author Adam Gleichsner
 * A GainFilter is a FIRFilter with some further specifications: all bValues except the first are also 0,
 * and the first bValue is referred to the gain
 */
public class GainFilter extends FIRFilter {

	/**
	 * Constructor for GainFilter, only needs the gain since we have once value 
	 * @param bValues
	 * @param N
	 * @throws InvalidObjectException
	 */
	public GainFilter(List<Double> gain) throws InvalidObjectException {
		super(gain, 0);
	}

}
