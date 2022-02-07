package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Emp;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Invoice;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.ItemProduct;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Job;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.enums.LogType;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.EmpDAO;

public class TransactionTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		EmpDAO empDAO = ctx.getBean("empDAO", EmpDAO.class);
		try {
			empDAO.addOneTx("txtx2", 20);
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
