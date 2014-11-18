package filters;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

/**
 * ScalarLinearFilter
 * @author Adam Gleichsner
 * Extending scalar filters, the scalar linear filter operates via all past
 * outputs and inputs multiplied by a bunch of constants.
 */
public class ScalarLinearFilter extends ScalarFilter {

	//Values for calculating output
	protected List<Double> inputs;
	protected List<Double> outputs;
	protected List<Double> aValues;
	protected List<Double> bValues;
	int N, M;
	
	/**
	 * Constructor checks that all input are good then initializes a
	 * fresh filter
	 * @param aValues
	 * @param bValues
	 * @param N
	 * @param M
	 * @throws InvalidObjectException
	 */
	public ScalarLinearFilter(List<Double> aValues, List<Double> bValues, int N, int M) throws InvalidObjectException {
		this.throwIfNullInput(aValues, bValues);
		this.throwIfSizesAreWrong(aValues.size(), bValues.size(), M, N);
		
		this.aValues = aValues;
		this.bValues = bValues;
		this.M = M;
		this.N = N;
		
		//M - 1 since we count from 1 to M
		this.inputs = this.createListOfRepeatedValues(M - 1, new Double(0));
		this.outputs = this.createListOfRepeatedValues(N, new Double(0));
	}

	@Override
	public void addInput(Double input) throws InvalidObjectException {
		this.throwIfNullInput(input);
		this.inputs.add(0, input);
		output = this.calculateOutput(input);
		this.outputs.add(0, output);
	}
	
	@Override
	protected Double calculateOutput(Double input) throws InvalidObjectException {
		Double inputSum = this.calculateListMultipliedSum(inputs, aValues);
		Double outputSum = this.calculateListMultipliedSum(outputs, bValues);
		
		return outputSum - inputSum;
	}
	
	@Override
	public void reset(Double reset) {
		
	}
	
	
	protected Double calculateListMultipliedSum (List<Double> inputs2, List<Double> aValues2) throws InvalidObjectException {
		this.throwIfNullInput(inputs2);
		Double msum = new Double(0);
		
		for(int count = 0; count < inputs2.size(); count++) {
			msum += inputs2.get(count) * aValues2.get(count);
		}
		
		return msum;
	}
	
	protected ArrayList<Double> createListOfRepeatedValues(int length, Double value) {
		ArrayList<Double> returnList = new ArrayList<Double>(length);
		for (int i = 0; i < length; i++) {
			returnList.add(i, value);
		}
		
		return returnList;
	}
	
	private void throwIfSizesAreWrong(int aValueSize, int bValueSize, int M, int N) {
		if (M != aValueSize || N != bValueSize)
			throw new IllegalArgumentException("Sizes don't match");
	}

}
