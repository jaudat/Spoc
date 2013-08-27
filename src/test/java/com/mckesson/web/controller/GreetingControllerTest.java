package com.mckesson.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.mckesson.domain.Color;
import com.mckesson.domain.Greeting;
import com.mckesson.service.GreetingService;
import com.mckesson.web.form.GreetingForm;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;

public class GreetingControllerTest extends TestCase {
	
	private GreetingForm greetingForm;
	Greeting greeting;
	Color color;
	Map<String, Object> model;
	GreetingService fakeGreetingService;
	List<Greeting> fakeGreetingList;
	GreetingController greetingController;
	
	@Before
	protected void setUp() {
		//first initialize the 2 parameters of the method addGreetingAndShowAll() for testing
		greetingForm = new GreetingForm(); //first parameter of addGreetingAndShowAll()
		greeting = new Greeting();
		greetingForm.setGreeting(greeting);
		color = new Color();
		greetingForm.setColor(color);
		model = new HashMap<String, Object>(); //second parameter of addGreetingAndShowAll()
		//mock the GreetingService
		fakeGreetingService = mock(GreetingService.class);
		fakeGreetingList = new ArrayList<Greeting>();
		fakeGreetingList.add(greeting);
		//inject the GreetingController with a fake GreetingService
		greetingController = new GreetingController(fakeGreetingService);
		when(fakeGreetingService.getAllGreetings()).thenReturn(fakeGreetingList);
	}
	
	//test that the greeting text should be inserted into a Greeting object
	//which ends up inside a list inside the model
	@Test
	public void testModelShouldContainNewGreetingText() {
		
		//GIVEN
		greeting.setGreetingText("hello world");
		//WHEN
		greetingController.addGreetingAndShowAll(greetingForm, model);
		//THEN
		List<Greeting> greetingListResult = (ArrayList<Greeting>) (model.get("greetinglist"));
		assertEquals("hello world", greetingListResult.get(0).getGreetingText());
	}
	
	//test that when the color red is selected, it is assigned correctly in the model
	@Test
	public void testModelShouldContainColorRedWhenSelected() {
		
		//GIVEN
		color.setColorCode("FF0000");
		//WHEN
		greetingController.addGreetingAndShowAll(greetingForm, model);
		//THEN
		assertEquals("FF0000", model.get("colorcode"));
	}
	
	//test that when no color is selected, the default color should be white
	//the color should end up inside the model and is called 'colorcode'
	@Test
	public void testModelShouldContainColorWhiteWhenNoColorIsSelected() {
		//GIVEN
		//no color value is initialized
		//WHEN
		greetingController.addGreetingAndShowAll(greetingForm, model);
		//THEN
		assertEquals("FFFFFF", model.get("colorcode"));
	}
	
	//test that the current date goes into the Greeting object inside the model
	@Test
	public void testModelShouldContainGreetingWithCurrentDate() {
		
		//GIVEN
		Date dateBeforeMethodCall = new Date();
		//WHEN
		greetingController.addGreetingAndShowAll(greetingForm, model);
		//THEN
		List<Greeting> greetingListResult = (ArrayList<Greeting>) (model.get("greetinglist"));
		Date resultDate = greetingListResult.get(0).getGreetingDate();
		assertEquals(dateBeforeMethodCall.getTime(), resultDate.getTime());
	}
	
	//test that when a new Greeting is created, it ends up inside a list inside the model
	@Test
	public void testNewGreetingShouldBeInsertedIntoList() {
		
		//WHEN
		greetingController.addGreetingAndShowAll(greetingForm, model);
		//THEN
		List<Greeting> greetingListResult = (ArrayList<Greeting>) (model.get("greetinglist"));
		assertNotNull(greetingListResult);
		assertEquals(1, greetingListResult.size());
	}
	
	//when the user skips directly to the greetings page without entering a greeting...
	//given @RequestMapping(value = "/greetings.html", method = RequestMethod.GET)
	//showAllGreetings() method should be called
	//and "greetings" should be returned and default color should be white
	@Test
	public void testShowAllGreetingsMethodShouldBeCalledWithGET() throws Exception {
		//GIVEN
		AnnotationMethodHandlerAdapter handlerAdapter = new AnnotationMethodHandlerAdapter();
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/home/greetings.html");
		MockHttpServletResponse response = new MockHttpServletResponse();
		//WHEN
		ModelAndView mav = handlerAdapter.handle(request, response, greetingController);
		//THEN
		assertEquals("greetings", mav.getViewName());
		assertEquals("FFFFFF", mav.getModel().get("colorcode"));
	}
}
