package com.test;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	@Test
    public void testDev()
    {
		App app = new App();
		String test = app.testDev();
		assertNotNull(test);
    }

}
