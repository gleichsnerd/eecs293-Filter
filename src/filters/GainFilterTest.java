package filters;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;
import java.util.ArrayList;

import org.junit.Test;

public class GainFilterTest {

	@Test
	public void testInit() throws InvalidObjectException {
		ArrayList<Double> gain = new ArrayList<Double>();
		gain.add(1d);
		GainFilter filter = new GainFilter(gain);
		
		try {
			filter.getOutput();
			fail("Filter with no input should throw");
		} catch (InvalidObjectException e) {}
	}
	
	@Test
	public void testInputOutputReset() throws InvalidObjectException {
		ArrayList<Double> gain = new ArrayList<Double>();
		gain.add(1d);
		GainFilter filter = new GainFilter(gain);
		
		filter.addInput(new Double(-1));
		assertEquals("Output should be -1", new Double(-1), filter.getOutput());
		
		filter.addInput(42d);
		assertEquals("Output should be 42", new Double(42), filter.getOutput());
		
		filter.addInput(new Double(-3));
		assertEquals("Output should be -3", new Double(-3), filter.getOutput());
		
		filter.reset(0d);
		
		filter.addInput(4d);
		assertEquals("Output should be 4", new Double(4), filter.getOutput());
	}

}
