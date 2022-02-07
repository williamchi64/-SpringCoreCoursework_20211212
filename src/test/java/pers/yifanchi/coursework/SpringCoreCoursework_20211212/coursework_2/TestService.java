package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestService {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/applicationContext2.xml");
		PersonServiceImpl personServiceImpl = ctx.getBean("personServiceImpl", PersonServiceImpl.class);
		personServiceImpl.findAllPeople().forEach(System.out::println);
		
	}
	
}
