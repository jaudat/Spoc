package com.mckesson.service;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.mckesson.dao.GreetingDao;
import com.mckesson.domain.Greeting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GreetingService {
	
	protected static Logger logger = Logger.getLogger("GreetingService");
	
	@Resource(name = "greetingDao")
	private GreetingDao greetingDao;
	
	public List<Greeting> getAllGreetings() {
		return greetingDao.getAllGreetings();
	}
	
	public void addGreeting(Greeting greeting) {
		greetingDao.addGreeting(greeting);
	}
}
