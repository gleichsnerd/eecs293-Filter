package filters;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;
import java.util.ArrayList;

import org.junit.Test;

public class ScalarLinearFilterTest {

	@Test
	public void testInit() throws InvalidObjectException {
		int M = 1;
		int N = 1;
		ArrayList<Double> aValues = new ArrayList<Double>();
		ArrayList<Double> bValues = new ArrayList<Double>();
		
		aValues.add(.1d);
		bValues.add(.5d);
		bValues.add(.5d);
		
		ScalarLinearFilter filter = new ScalarLinearFilter(aValues, bValues, M, N);
		
		try {
			filter.getOutput();
			fail("Filter with no inputs should throw");
		} catch (InvalidObjectException e) {}
	}
	
	@Test
	public void testInputOutputReset() throws InvalidObjectException {
		int M = 1;
		int N = 1;
		ArrayList<Double> aValues = new ArrayList<Double>();
		ArrayList<Double> bValues = new ArrayList<Double>();
		
		aValues.add(.1d);
		bValues.add(.5d);
		bValues.add(.5d);
		
		ScalarLinearFilter filter = new ScalarLinearFilter(aValues, bValues, M, N);
		
		filter.addInput(-1d);
		assertEquals("Should output -.5", new Double(-.5), filter.getOutput());
		
		filter.addInput(1d);
		assertEquals("Should output .05", new Double(.05), filter.getOutput());
		
		filter.addInput(2d);
		assertEquals("Should output 1.495", new Double(1.495), filter.getOutput());
		
		filter.reset(new Double (0));
		
		filter.addInput(-1d);
		assertEquals("Should output -.5", new Double(-.5), filter.getOutput());
		
		filter.addInput(3d);
		assertEquals("Should output 1.05", new Double(1.05), filter.getOutput());
		
		filter.addInput(1d);
		assertEquals("Should output 1.895", new Double(1.895), filter.getOutput());
		
		filter.addInput(2d);
		assertEquals("Should output 1.3105", new Double(1.3105), filter.getOutput());
		
		filter.addInput(1d);
		assertEquals("Should output 1.36895", new Double(1.36895), filter.getOutput());
	}

}
