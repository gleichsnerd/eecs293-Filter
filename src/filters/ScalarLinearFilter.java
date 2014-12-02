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
	public ScalarLinearFilter(List<Double> aValues, List<Double> bValues, int M, int N) throws InvalidObjectException {
		this.throwIfNullInput(aValues, bValues);
		this.throwIfSizesAreWrong(aValues.size(), bValues.size(), M, N);
		
		this.aValues = aValues;
		this.bValues = bValues;
		this.M = M;
		this.N = N;
		
		//M - 1 since we count from 1 to M
		if (M > 0)
			this.inputs = this.createListOfRepeatedValues(M - 1, 0d);
		else
			this.inputs = new ArrayList<Double>();
		
		if (N > 0)
			this.outputs = this.createListOfRepeatedValues(N, 0d);
		else
			this.outputs = new ArrayList<Double>();
	}

	/**
	 * {@inheritDoc}
	 * @param input
	 * @throws InvalidObjectException
	 */
	@Override
	public void addInput(Double input) throws InvalidObjectException {
		this.throwIfNullInput(input);
		//Elements from 0 to N
		if (inputs.size() <= N)
			this.inputs.add(0, input);
		else {
			this.inputs.remove(N);
			this.inputs.add(0, input);
		}
			
		output = this.calculateOutput(input);
		//Elements from 1 to M
		if (outputs.size() <= M )
			this.outputs.add(0, output);
		else if (M > 0) {
			this.outputs.remove(M - 1);
			this.outputs.add(0, output);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @param input
	 * @return Double - output of the filter
	 * @throws InvalidObjectException
	 */
	@Override
	protected Double calculateOutput(Double input) throws InvalidObjectException {
		Double inputSum = this.calculateListMultipliedSum(inputs, bValues);
		Double outputSum = this.calculateListMultipliedSum(outputs, aValues);
		
		return inputSum - outputSum;
	}
	
	/**
	 * {@inheritDoc}
	 * @param reset
	 * @throws InvalidObjectException
	 */
	@Override
	public void reset(Double reset) throws InvalidObjectException {
		Double outputOverwrite = reset * this.calculatelistSum(bValues) / (1 + this.calculatelistSum(aValues));
		
		inputs = this.createListOfRepeatedValues(N, reset);
		outputs = this.createListOfRepeatedValues(M, outputOverwrite);
	}
	
	/**
	 * Calculates the sum of a list of doubles
	 * @param list
	 * @return Double - sum
	 * @throws InvalidObjectException
	 */
	protected Double calculatelistSum (List<Double> list) throws InvalidObjectException {
		this.throwIfNullInput(list);
		Double sum = 0d;
		//For each input, add it to the sum
		for (int count = 0; count < inputs.size(); count ++) {
			sum += inputs.get(count);
		}
		
		return sum;
	}
	
	/**
	 * Calculated the sum of each element times its corresponding
	 * multiplier found in a or b values.
	 * @param inputs
	 * @param aValues
	 * @return Double - multiplicative sum
	 * @throws InvalidObjectException
	 */
	protected Double calculateListMultipliedSum (List<Double> in, List<Double> mult) throws InvalidObjectException {
		this.throwIfNullInput(in);
		Double msum = 0d;
		//For each item in the list
		for(int count = 0; count < in.size() && count < mult.size(); count++) {
			msum += in.get(count) * mult.get(count);
		}
		return msum;
	}
	
	/**
	 * Creates a new list given a length and a value to repeat across
	 * every character of the game
	 * @param length
	 * @param value
	 * @return ArrayList<Double> - new list of repeated elements
	 */
	protected ArrayList<Double> createListOfRepeatedValues(int length, Double value) {
		ArrayList<Double> returnList = new ArrayList<Double>(length);
		
		for (int i = 0; i < length; i++) {
			returnList.add(i, value);
		}
		return returnList;
	}
	
	/**
	 * Throws if there are discrepancies between buffers and multiplier sizes
	 * @param aValueSize
	 * @param bValueSize
	 * @param M
	 * @param N
	 */
	private void throwIfSizesAreWrong(int aValueSize, int bValueSize, int M, int N) {
		if (M != aValueSize || N != bValueSize - 1)
			throw new IllegalArgumentException("Sizes don't match");
	}

}
