package filters;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;

import org.junit.Test;

public class FilterCascadeTest {

	@Test
	public void testInit() {
		Filter<Double> cascade = new FilterCascade<Double>();
		
		try {
			cascade.getOutput();
			fail("Cascade with no filters should throw");
		} catch (InvalidObjectException e) {}
	}
	
	@Test
	public void testInputOutput() throws InvalidObjectException {
		FilterCascade<Double> cascade = new FilterCascade<Double>();
		
		//Test one filter
		ScalarFilter max = new MaxFilter();
		cascade.addFilter(max);
		
		cascade.addInput(2d);
		assertEquals("Output should be the only input", new Double(2), cascade.getOutput());
		
		cascade.addInput(3d);
		assertEquals("Output should be new input", new Double(3), cascade.getOutput());
		
		//Test two filters
		ScalarFilter minN = new MinFilterN(2);
		cascade.addFilter(minN);

		cascade.addInput(27d);
		cascade.addInput(28d);
		cascade.addInput(29d);
		
		assertEquals("Output should be 28 since 27 is erased when 29 is inserted", new Double(28), cascade.getOutput());
	}
	
	@Test
	public void testReset() throws InvalidObjectException {
		FilterCascade<Double> cascade = new FilterCascade<Double>();
		
		ScalarFilter max = new MaxFilter();
		ScalarFilter minN = new MinFilterN(2);
		cascade.addFilter(max);
		cascade.addFilter(minN);
		
		try {
			cascade.reset(42d);
			fail("Reset on a cascade should throw");
		} catch (IllegalArgumentException e) {}
		
		try {
			cascade.resetFilterByIndex(-1, 12d);
			fail("Negative index should throw");
		} catch (IllegalArgumentException e) {}
		
		try {
			cascade.resetFilterByIndex(100, 12d);
			fail("Too large index should throw");
		} catch (IllegalArgumentException e) {}
		
		cascade.addInput(new Double (1));
		cascade.resetFilterByIndex(0, 0d);
		
		assertEquals("Reset on first filter shouldn't change output unless propagated", new Double(1), cascade.getOutput());
		assertEquals("Reset should exist on first filter", new Double(0), max.getOutput());
	}

}
