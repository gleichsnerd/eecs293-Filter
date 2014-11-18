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
		
		cascade.addInput(new Double(2));
		assertEquals("Output should be the only input", new Double(2), cascade.getOutput());
		
		cascade.addInput(new Double(3));
		assertEquals("Output should be new input", new Double(3), cascade.getOutput());
		
		//Test two filters
		ScalarFilter minN = new MinFilterN(2);
		cascade.addFilter(minN);

		cascade.addInput(new Double(27));
		cascade.addInput(new Double(28));
		cascade.addInput(new Double(29));
		
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
			cascade.reset(new Double(42));
			fail("Reset on a cascade should throw");
		} catch (IllegalArgumentException e) {}
		
		try {
			cascade.resetFilterByIndex(-1, new Double(12));
			fail("Negative index should throw");
		} catch (IllegalArgumentException e) {}
		
		try {
			cascade.resetFilterByIndex(100, new Double(12));
			fail("Too large index should throw");
		} catch (IllegalArgumentException e) {}
		
		cascade.addInput(new Double (1));
		cascade.resetFilterByIndex(0, new Double(0));
		
		assertEquals("Reset on first filter shouldn't change output unless propagated", new Double(1), cascade.getOutput());
		assertEquals("Reset should exist on first filter", new Double(0), max.getOutput());
	}

}
