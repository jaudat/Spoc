package com.mckesson.dao;

import java.util.List;
import com.mckesson.domain.Greeting;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingDao {
	
	public List<Greeting> getAllGreetings();
	
	public void addGreeting(Greeting greeting);
}
