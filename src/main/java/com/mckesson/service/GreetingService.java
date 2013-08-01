package com.mckesson.service;
 
import java.util.List;
import org.apache.log4j.Logger;
import com.mckesson.dao.GreetingDao;
import com.mckesson.domain.Greeting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
 
@Service
@Transactional
public class GreetingService {
 
	protected static Logger logger = Logger.getLogger("GreetingService");
 
	@Autowired
	private GreetingDao greetingDao;
 
	public List<Greeting> getAllGreetings() {		
		return greetingDao.getAllGreetings();		
	}
 
	public void addGreeting(Greeting greeting) {		
		greetingDao.addGreeting(greeting);
	}
 
}