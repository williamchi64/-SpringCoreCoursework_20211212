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
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.InvoiceItemItemProductDAO;

public class TestInvoiceItemItemProductDAO {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		InvoiceItemItemProductDAO iIIPDAO = ctx.getBean("invoiceItemItemProductDAO", InvoiceItemItemProductDAO.class);
		List<Invoice> invoices = iIIPDAO.queryAll();
//		List<Invoice> invoices = iIIPDAO.queryById(1);
		if (invoices == null) {
			System.out.println("null");
		} else {
			invoices.forEach(System.out::println);
		}
	}
}
