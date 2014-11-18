package filters;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;

import org.junit.Test;

public class MaxFilterNTest {

	@Test
	public void testInit() {
		ScalarFilter filter = new MaxFilterN(3);
		
		try {
			filter.getOutput();
			fail("Filter with no inputs should err");
		} catch (InvalidObjectException e) {}
	}
	
	@Test
	public void testInputOutput() throws InvalidObjectException {
		Double input = (double) 2;
		MaxFilter filter = new MaxFilterN(2);
		
		filter.addInput(input);
		assertEquals("Only value should be output", input, filter.getOutput());
		
		input = (double) 3;
		filter.addInput(input);
		assertEquals("New value should be output", input, filter.getOutput());
		
		input = (double) 1;
		filter.addInput(input);
		assertNotEquals("Old value should still be output", input, filter.getOutput());
		
		input = (double) 2;
		filter.addInput(input);
		assertEquals("Old max should be popped due to age", input, filter.getOutput());
	}

	@Test
	public void testReset() throws InvalidObjectException {
		Double reset = (double) 0;
		MaxFilterN filter = new MaxFilterN(2);
		
		filter.addInput((double) 27);
		filter.reset(reset);
		
		assertEquals("Output should be the reset value", reset, filter.getOutput());
	}

}
