package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.BookController;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.TestDAO;

public class TestAOP {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		TestDAO testDAO = ctx.getBean("testDAO", TestDAO.class);
//		testDAO.test(1,2,3,45,2,3,54,2,4,1,3,4,1,34,1535,35242,13).forEach(i->System.out.println(i));
		System.out.println(testDAO.test(1,2));
		System.out.println(testDAO.test2(1,1));
	}

}
