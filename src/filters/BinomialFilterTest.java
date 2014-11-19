package filters;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;
import java.util.ArrayList;

import org.junit.Test;

public class BinomialFilterTest {

	@Test
	public void testInit() throws InvalidObjectException {
		int N = 3;
		BinomialFilter filter = new BinomialFilter(N);
		
		try {
			filter.getOutput();
			fail("Filter with no input should throw");
		} catch (InvalidObjectException e) {}
	}
	
	@Test
	public void testInputOutputReset() throws InvalidObjectException {
		int N = 3;
		
		BinomialFilter filter = new BinomialFilter(N);
		
		filter.addInput(new Double(-1));
		assertEquals("Output should be -1", new Double(-1), filter.getOutput());
		
		filter.addInput(new Double(-3));
		assertEquals("Output should be -9.75", new Double(-9.75), filter.getOutput());
		
		filter.reset(new Double(0));
		
		filter.addInput(new Double(4));
		assertEquals("Output should be 4", new Double(4), filter.getOutput());
	}

}
