package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testPersonDAO {
	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/applicationContext2.xml");
		PersonDAO personDAO = ctx.getBean("personDAOImpl", PersonDAOImpl.class);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		boolean check = personDAO.create(new Person("Marry", 0, sdf.parse("1999/01/01")));
//		System.out.println(check);
		personDAO.getAll().forEach(System.out::println);
		
	}
}
