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
		Double input = (double) 2;
		AveragingFilter filter = new AveragingFilterN(2);
		
		filter.addInput(input);
		assertEquals("Only value should be output", input, filter.getOutput());
		
		input = (double) 3;
		filter.addInput(input);
		assertEquals("Average should now be 2.5", new Double(2.5), filter.getOutput());
		
		input = (double) 1;
		filter.addInput(input);
		assertNotEquals("Average should now be 2", new Double(2), filter.getOutput());
		
		input = (double) 2;
		filter.addInput(input);
		assertEquals("Average should now be 1.5", new Double(1.5), filter.getOutput());
	}

	@Test
	public void testReset() throws InvalidObjectException {
		Double reset = (double) 0;
		AveragingFilterN filter = new AveragingFilterN(2);
		
		filter.addInput((double) 27);
		filter.reset(reset);
		
		assertEquals("Output should be the reset value", reset, filter.getOutput());
	}

}
