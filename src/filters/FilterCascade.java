package filters;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

/**
 * FilterCascade
 * @author Adam Gleichsner
 * This one is tricky. Although it's a filter by standards, it contains a multitude of internal filters.
 * The other caveat is that it can't be reset, so we have to put in an alternative means for resetting a
 * filter (either by index or by allowing access to the independent filters)
 * @param <T>
 */
public class FilterCascade<T> extends Filter<T> {
	//Holds all filters in cascade
	private List<Filter<T>> filters;
	
	public FilterCascade() {
		filters = new ArrayList<Filter<T>>();
	}
	
	/**
	 * {@inheritDoc}
	 * @param input
	 * @throws InvalidObjectException
	 */
	@Override
	public void addInput(T input) throws InvalidObjectException {
		this.throwIfNullInput(input);
		output = this.cascadeInput(input);
	}

	/**
	 * {@inheritDoc}
	 * @param reset
	 * @throws InvalidObjectException
	 */
	@Override
	public void reset(T reset) throws InvalidObjectException {
		throw new IllegalArgumentException("Cascade itself cannot be reset");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws InvalidObjectException
	 */
	@Override
	protected void throwIfInvalidOutput() throws InvalidObjectException {
		if (output == null || filters == null || filters.isEmpty())
			throw new InvalidObjectException("Output is invalid");
	}
	
	/**
	 * Method to allow resetting of a specific filter in the cascade by referencing
	 * it by index
	 * @param index
	 * @param reset
	 * @throws IllegalArgumentException
	 * @throws InvalidObjectException
	 */
	public void resetFilterByIndex(int index, T reset) throws IllegalArgumentException, InvalidObjectException {
		if (index < 0 || index > filters.size() - 1)
			throw new IllegalArgumentException("Index is invalid");
		
		filters.get(index).reset(reset);
	}
	
	/**
	 * Method to print each filter in order from filters list
	 * @throws InvalidObjectException
	 */
	public void listFilters() throws InvalidObjectException {
		if (filters == null || filters.isEmpty())
			throw new InvalidObjectException("No filters present");
		
		for (Filter<T> filter: filters) {
			System.out.println(filter.toString());
		}
	}
	
	/**
	 * Method to add filters to the cascade
	 * @param filter
	 * @throws InvalidObjectException
	 */
	public void addFilter(Filter<T> filter) throws InvalidObjectException {
		this.throwIfNullInput(filter);
		filters.add(filter);
	}
	
	/**
	 * Method to remove a filter from the cascade given a valid index
	 * @param index
	 * @throws InvalidObjectException
	 */
	public void removeFilter(int index) throws InvalidObjectException {
		if (index < 0 || index > filters.size() - 1)
			throw new IllegalArgumentException("Index is invalid");
		
		filters.remove(index);
	}
	
	//Private methods
	
	/**
	 * Private helper that inputs to a filter, grabs the output, and then
	 * feeds it into the next filter
	 * @param input
	 * @return T - output of cascade
	 * @throws InvalidObjectException
	 */
	private T cascadeInput(T input) throws InvalidObjectException {
		this.throwIfNullInput(input);
		T currentValue = input;
		
		//For each filter, move the output to the input
		for(Filter<T> filter: filters) {
			filter.addInput(currentValue);
			currentValue = filter.getOutput();
		}
		
		return currentValue;
	}

}
