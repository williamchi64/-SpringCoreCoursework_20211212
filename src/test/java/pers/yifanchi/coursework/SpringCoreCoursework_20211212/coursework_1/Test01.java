package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 
 * @author 齊毅凡-GJUN線上學員
 *
 */
public class Test01 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/applicationContext1.xml");
		// get all teachers
		Person[] teachers = {ctx.getBean("t1", Teacher.class), ctx.getBean("t2", Teacher.class)};
		// target person
		Person marry = ctx.getBean("s2", Student.class);
		// get marry's teachers name by loop, collected by list
		List<String> marryTeacehrs1 = new ArrayList<String>();
		for (Person teacher: teachers) {
			for (Clazz teacherClazz: teacher.getClazzes()){
				for (Clazz marryClazz:marry.getClazzes()) {
					if (teacherClazz.getId()==marryClazz.getId()) {
						marryTeacehrs1.add(teacher.getName());
					}
				}
			}
		}
		System.out.println(marryTeacehrs1);
		// use java 8 forEach replace loop, collected by list
		List marryTeacehrs2 = new ArrayList();
		marry.getClazzes().forEach(marryClazz->{
			Arrays.asList(teachers).stream().forEach(teacher->{
				teacher.getClazzes().forEach(teacherClazz->{
					if (marryClazz.getId()==teacherClazz.getId())
						marryTeacehrs2.add(teacher.getName());
				});
			});
		});
		System.out.println(marryTeacehrs2);
		// use map to get data in a collection, collected by list
		List marryTeacehrs3 = new ArrayList();
		marry.getClazzes().forEach(marryClazz->{
			Arrays.asList(teachers).stream().forEach(teacher->{
				marryTeacehrs3.add(
					teacher.getClazzes().stream()
						.filter(teacherClazz->teacherClazz.getId()==marryClazz.getId())
						.map(teacherClazz->teacher.getName())
						.collect(Collectors.toList())
				);
			});
		});
		System.out.println(marryTeacehrs3);
	}
}
