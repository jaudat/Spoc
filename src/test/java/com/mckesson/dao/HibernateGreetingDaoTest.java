package com.mckesson.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.hibernate.*;
import org.hibernate.classic.Session;
import junit.framework.TestCase;
import com.mckesson.domain.Greeting;

public class HibernateGreetingDaoTest extends TestCase {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Query query;
	
	@Before
	public void setUp() {
		sessionFactory = mock(SessionFactory.class);
		session = mock(Session.class);
		query = mock(Query.class);
	}
	
	//testing hibernate and the method getAllGreetings().
	//create an expected list, use Mockito to return expected list upon query.list().
	//verify that the expected list = the actual list from hibernateGreetingDao.getAllGreetings().
	@Test
	public void testTheMethodGetAllShouldReturnAListOfGreetings() {
		
		//GIVEN
		String hql = "select g from Greeting g order by id desc";
		List<Greeting> expectedGreetingList = new ArrayList<Greeting>();
		Greeting greeting1 = new Greeting();
		greeting1.setGreetingText("Welcome to the world!");
		greeting1.setGreetingDate(new Date());
		expectedGreetingList.add(greeting1);
		Greeting greeting2 = new Greeting();
		greeting2.setGreetingText("Hello there everyone...");
		greeting2.setGreetingDate(new Date());
		expectedGreetingList.add(greeting2);
		Greeting greeting3 = new Greeting();
		greeting3.setGreetingText("Hey there");
		greeting3.setGreetingDate(new Date());
		expectedGreetingList.add(greeting3);
		//WHEN
		HibernateGreetingDao hibernateGreetingDao = new HibernateGreetingDao();
		hibernateGreetingDao.setSessionFactory(sessionFactory);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery(hql)).thenReturn(query);
		when(query.list()).thenReturn(expectedGreetingList);
		List<Greeting> actualGreetingList = hibernateGreetingDao.getAllGreetings();
		//THEN
		assertNotNull(actualGreetingList);
		assertSame(expectedGreetingList, actualGreetingList);
	}
}
