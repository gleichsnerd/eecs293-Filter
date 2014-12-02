package filters;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

/**
 * BinomialFilter
 * @author Adam Gleichsner
 * This last filter is again a FIRFilter, but this time the bValues are combinations 
 * of the total size of the list and their position in said list.
 */
public class BinomialFilter extends FIRFilter {

	/**
	 * Constructor that generates the list of binomials
	 * @param N
	 * @throws InvalidObjectException
	 */
	public BinomialFilter(final int N) throws InvalidObjectException {
		super(getBinomials(N), N);		
	}
	
	/**
	 * Helper to get the binomials for the filter
	 * @param N
	 * @return ArrayList<Double> - list of binomials
	 */
	private static ArrayList<Double> getBinomials(int N) {
		ArrayList<Double> returnList = new ArrayList<Double>();
		for(int i = 0; i <= N; i++) {
			returnList.add(combination(N, i));
		}
		
		return returnList;
	}
	
	/**
	 * Private helper to calculate a combination
	 * @param n - total items
	 * @param k - chosen from
	 * @return Double - Combination
	 */
	private static Double combination(int n, int k) {
	    return factorial(n) / (factorial(k) * factorial(n - k));
	}

	/**
	 * Private helper to calculate a permutation
	 * @param i - number to permute
	 * @return Double - Permutation
	 */
	private static Double factorial(int i) {
	    Double factorial = 1d;
		for (int count = i; i > 0; i--) {
	    	factorial = factorial * count;
	    }
		return factorial;
	}

}
