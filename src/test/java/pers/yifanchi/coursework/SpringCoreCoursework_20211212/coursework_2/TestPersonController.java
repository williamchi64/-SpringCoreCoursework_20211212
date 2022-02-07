package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonController {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/applicationContext2.xml");
		PersonController personController = ctx.getBean("personController", PersonController.class);
		
//		personController.addPerson("Bob", 2001, 01, 01);
		personController.findAllPeople().forEach(System.out::println);
	}

}
