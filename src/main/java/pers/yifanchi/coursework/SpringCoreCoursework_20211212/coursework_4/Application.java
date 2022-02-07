package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.view.BookOrderSystem;

public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		BookOrderSystem bookOrderSystem = ctx.getBean("bookOrderSystem", BookOrderSystem.class);
		bookOrderSystem.start();
		((AbstractApplicationContext) ctx).close();
	}

}
