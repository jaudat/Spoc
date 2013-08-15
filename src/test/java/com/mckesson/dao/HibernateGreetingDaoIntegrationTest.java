package com.mckesson.dao;

import java.io.File;
import java.util.Date;
import java.util.List;
import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mckesson.domain.Greeting;

public class HibernateGreetingDaoIntegrationTest extends TestCase {
	
	private SessionFactory sessionFactory;
	private Session session;
	HibernateGreetingDao hibernateGreetingDao;
	
	private SessionFactory getSessionFactory() throws Exception {
		return createConfiguration().buildSessionFactory();
	}
	
	private Configuration createConfiguration() throws Exception {
		Configuration cfg = loadConfiguration();
		return cfg;
	}
	
	private Configuration loadConfiguration() {
		return new AnnotationConfiguration()
			.addAnnotatedClass(Greeting.class)
			.configure(new File("/Users/zkhan/workspace/Spoc/src/test/resources/hibernatetest.cfg.xml"));
	}
	
	@Before
	protected void setUp() throws Exception {
		sessionFactory = getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		hibernateGreetingDao = new HibernateGreetingDao();
		hibernateGreetingDao.setSessionFactory(sessionFactory);
	}
	
	@After
	protected void tearDown() throws Exception {
		session.getTransaction().rollback();
	}
	
	@Test
	public void testNoGreetingsInDBShouldResultInEmptyList() throws Exception {
		//WHEN
		List<Greeting> actualGreetingsList = hibernateGreetingDao.getAllGreetings();
		//THEN
		assertEquals(0, actualGreetingsList.size());
	}
	
	@Test
	public void testSaveOneGreetingAndReadShouldBeSuccessful() throws Exception {
		//GIVEN
		Greeting expectedGreeting = new Greeting();
		expectedGreeting.setGreetingText("Hello! Inserting the 1st greeting");
		expectedGreeting.setGreetingDate(new Date());
		//WHEN
		hibernateGreetingDao.addGreeting(expectedGreeting);
		List<Greeting> actuaGreetingsList = hibernateGreetingDao.getAllGreetings();
		//THEN
		assertEquals(1, actuaGreetingsList.size());
		assertEquals(expectedGreeting, actuaGreetingsList.get(0));
	}
	
	@Test
	public void testSaveThreeGreetingsAndReadShouldBeSuccessful() throws Exception {
		//GIVEN
		Greeting expectedGreeting1 = new Greeting();
		expectedGreeting1.setGreetingText("Hello! Inserting the 1st greeting");
		expectedGreeting1.setGreetingDate(new Date());
		hibernateGreetingDao.addGreeting(expectedGreeting1);
		Greeting expectedGreeting2 = new Greeting();
		expectedGreeting2.setGreetingText("Hello! Inserting the 2nd greeting");
		expectedGreeting2.setGreetingDate(new Date());
		hibernateGreetingDao.addGreeting(expectedGreeting2);
		Greeting expectedGreeting3 = new Greeting();
		expectedGreeting3.setGreetingText("Hello! Inserting the 3rd greeting");
		expectedGreeting3.setGreetingDate(new Date());
		hibernateGreetingDao.addGreeting(expectedGreeting3);
		//WHEN
		List<Greeting> actualGreetingsList = hibernateGreetingDao.getAllGreetings();
		//THEN
		assertEquals(3, actualGreetingsList.size());
		//the list is sorted correctly (order by id desc)
		assertEquals(expectedGreeting3.getId(), actualGreetingsList.get(0).getId());
	}
}
