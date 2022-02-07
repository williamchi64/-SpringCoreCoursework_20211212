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
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.ItemProductItemDAO;

public class TestItemProductItemDAO {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		ItemProductItemDAO iPIDAO = ctx.getBean("itemProductItemDAO", ItemProductItemDAO.class);
//		List<ItemProduct> itemProducts = iPIDAO.queryAll();
		List<ItemProduct> itemProducts = iPIDAO.queryById(1);
		itemProducts.forEach(itemProduct->System.out.println(itemProduct));
	}
}
