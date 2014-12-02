package filters;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;
import java.util.ArrayList;

import org.junit.Test;

public class FIRFilterTest {

	@Test
	public void testInit() throws InvalidObjectException {
		int N = 1;
		ArrayList<Double> bValues = new ArrayList<Double>();
		
		bValues.add(new Double(.5));
		bValues.add(new Double(.5));

		FIRFilter filter = new FIRFilter(bValues, N);
		
		try {
			filter.getOutput();
			fail("Filter with no input should throw");
		} catch (InvalidObjectException e) {}
	}
	
	@Test
	public void testInputOutputReset() throws InvalidObjectException {
		int N = 1;
		ArrayList<Double> bValues = new ArrayList<Double>();
		
		bValues.add(new Double(.5));
		bValues.add(new Double(.5));

		FIRFilter filter = new FIRFilter(bValues, N);
		
		filter.addInput(new Double(-1));
		assertEquals("Output should be -.5", new Double(-.5), filter.getOutput());
		
		filter.addInput(new Double(-1));
		assertEquals("Output should be -1", new Double(-1), filter.getOutput());
		
		filter.addInput(new Double(-3));
		assertEquals("Output should be -2", new Double(-2), filter.getOutput());
		
		filter.reset(0d);
		
		filter.addInput(4d);
		assertEquals("Output should be 2", new Double(2), filter.getOutput());
	}

}
