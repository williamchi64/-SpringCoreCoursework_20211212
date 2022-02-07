package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Emp;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Job;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.enums.LogType;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.EmpDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.EmpJobDAO;

public class TestEmpJobDAO {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		EmpJobDAO empJobDAO = ctx.getBean("empJobDao", EmpJobDAO.class);
//		List<Emp> emps = empJobDao.queryAll1();
//		emps.forEach(emp->System.out.println(emp));
//		List<Job> jobs = empJobDao.queryAll2();
//		jobs.forEach(job->System.out.println(job));
//		List<Emp> emps = empJobDao.queryEmp();
//		emps.forEach(emp->System.out.println(emp));
//		List<Job> jobs = empJobDAO.queryJob();
//		jobs.forEach(job->System.out.println(job));
	}
}
