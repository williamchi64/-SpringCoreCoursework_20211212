package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Emp;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.enums.LogType;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.EmpDAO;

public class TestEmpDAO2 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		EmpDAO empDAO = ctx.getBean("empDAO", EmpDAO.class);
//		List<Emp> emps = empDao.queryListEmp1();
//		List<Emp> emps = empDao.queryListEmp2();
//		int num = empDao.addOne1("Alice", 39);
//		int num = empDao.addOne2("Amy", 45);
		int num = empDAO.updateById(2, "Tomey", 33);
//		int num = empDao.deleteById(1);
		System.out.println(num);
	}
}
