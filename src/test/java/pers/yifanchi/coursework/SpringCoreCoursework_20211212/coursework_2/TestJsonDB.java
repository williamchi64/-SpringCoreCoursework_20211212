package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJsonDB {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/applicationContext2.xml");
		JsonDB jsonDB = ctx.getBean("jsonDB", JsonDB.class);
		jsonDB.queryAll().forEach(System.out::println);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		boolean check = jsonDB.add(new Person("John", 0, sdf.parse("2000/01/01")));
//		System.out.println(check);
//		System.out.println(jsonDB.queryAll());
		
	}
}
