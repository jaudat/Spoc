package com.mckesson.web.controller;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.mockito.Mockito.*;
import com.mckesson.service.GreetingService;
import junit.framework.TestCase;

public class GreetingControllerTest extends TestCase {
	
	@Test
	public void testTheMethodShowAllGreetingsShouldReturnAResult() {
		
		//GIVEN
		GreetingService fakeGreetingService = mock(GreetingService.class);
		GreetingController controller = new GreetingController(fakeGreetingService);
		Map<String, Object> model = new HashMap<String, Object>();
		//WHEN
		String result = controller.showAllGreetings(model);
		//THEN
		assertNotNull(result);
		assertEquals("greetings", result);
	}
}
