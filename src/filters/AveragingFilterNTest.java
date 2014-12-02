package filters;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;

import org.junit.Test;

public class AveragingFilterNTest {

	@Test
	public void testInit() {
		ScalarFilter filter = new AveragingFilterN(3);
		
		try {
			filter.getOutput();
			fail("Filter with no inputs should err");
		} catch (InvalidObjectException e) {}
	}
	
	@Test
	public void testInputOutput() throws InvalidObjectException {
		Double input = 2d;
		AveragingFilter filter = new AveragingFilterN(2);
		
		filter.addInput(input);
		assertEquals("Only value should be output", input, filter.getOutput());
		
		input = 3d;
		filter.addInput(input);
		assertEquals("Average should now be 2.5", new Double(2.5), filter.getOutput());
		
		input = 1d;
		filter.addInput(input);
		assertNotEquals("Average should now be 2", 2d, filter.getOutput());
		
		input = 2d;
		filter.addInput(input);
		assertEquals("Average should now be 1.5", new Double(1.5), filter.getOutput());
	}

	@Test
	public void testReset() throws InvalidObjectException {
		Double reset = 0d;
		AveragingFilterN filter = new AveragingFilterN(2);
		
		filter.addInput(27d);
		filter.reset(reset);
		
		assertEquals("Output should be the reset value", reset, filter.getOutput());
	}

}
