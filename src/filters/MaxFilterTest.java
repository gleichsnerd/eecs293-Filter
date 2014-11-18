package filters;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;

import org.junit.Test;

public class MaxFilterTest {

	@Test
	public void testInit() {
		ScalarFilter filter = new MaxFilter();
		
		try {
			filter.getOutput();
			fail("Filter without input should throw");
		} catch (InvalidObjectException e) {}
	}
	
	@Test
	public void testInputOutput() throws InvalidObjectException {
		Double input = (double)1;
		Filter<Double> filter = new MaxFilter();
		
		filter.addInput(input);
		assertEquals("Only input should be the output", input, filter.getOutput());
		
		input = (double)0;
		filter.addInput(input);
		assertNotEquals("Smaller input shouldn't be the output", input, filter.getOutput());
		
		input = (double)3;
		filter.addInput(input);
		assertEquals("Larger input should be the output", input, filter.getOutput());
	}
	
	@Test
	public void testReset() throws InvalidObjectException {
		Double reset = (double)0;
		ScalarFilter filter = new MaxFilter();
		
		filter.addInput((double)42);
		filter.reset(reset);
		
		assertEquals("Reset value should be the new output", reset, filter.getOutput());
	}

}
