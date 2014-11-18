package filters;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;

import org.junit.Test;

public class IdentityFilterTest {

	@Test
	public void testNewFilter() {
		Filter<String> filter = new IdentityFilter<String>();
		
		try {
			filter.getOutput();
			fail("Filter with no inputs should have no outputs");
		} catch (InvalidObjectException e) {}
	}
	
	@Test
	public void testInputOutput() throws InvalidObjectException {
		Filter <String> filter = new IdentityFilter<String>();
		
		filter.addInput("1");
		assertEquals("Input given should be the same as output", "1", filter.getOutput());
		
		filter.addInput("2");
		assertEquals("New input should now be output", "2", filter.getOutput());
	}
	
	@Test
	public void testReset() throws InvalidObjectException {
		Filter<String> filter = new IdentityFilter<String>();
		
		filter.addInput("1");
		assertEquals("Input given should be the same as output", "1", filter.getOutput());
		
		filter.reset("Ponies!");
		assertEquals("Output should be set to reset value", "Ponies!", filter.getOutput());
	}

}
