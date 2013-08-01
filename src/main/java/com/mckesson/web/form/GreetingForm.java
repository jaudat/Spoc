package com.mckesson.web.form;
 
import com.mckesson.domain.Color;
import com.mckesson.domain.Greeting;
 
public class GreetingForm {
 
	//the domain model persistent data
	private Greeting greeting;
 
	//the other non persistent data ... used in user interface only
	private Color color;
 
	public Greeting getGreeting() {
		return greeting;
	}
 
	public void setGreeting(Greeting greeting) {
		this.greeting = greeting;
	}
 
	public Color getColor() {
		return color;
	}
 
	public void setColor(Color color) {
		this.color = color;
	}
 
}