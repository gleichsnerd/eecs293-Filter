package filters;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;

import org.junit.Test;

public class AveragingFilterTest {

	@Test
	public void testInit() {
		ScalarFilter filter = new AveragingFilter();
		
		try {
			filter.getOutput();
			fail("Filter without input should throw");
		} catch (InvalidObjectException e) {}
	}
	
	@Test
	public void testInputOutput() throws InvalidObjectException {
		Double input = (double)1;
		Filter<Double> filter = new AveragingFilter();
		
		filter.addInput(input);
		assertEquals("One input should be the average", input, filter.getOutput());
		
		input = (double)0;
		filter.addInput(input);
		assertEquals("New average should be .5", new Double(.5), filter.getOutput());
		
		input = (double)2;
		filter.addInput(input);
		assertEquals("New average should be 1 again", new Double(1), filter.getOutput());
	}
	
	@Test
	public void testReset() throws InvalidObjectException {
		Double reset = (double)0;
		ScalarFilter filter = new AveragingFilter();
		
		filter.addInput((double)42);
		filter.reset(reset);
		
		assertEquals("Reset value should be the new output", reset, filter.getOutput());
	}

}
