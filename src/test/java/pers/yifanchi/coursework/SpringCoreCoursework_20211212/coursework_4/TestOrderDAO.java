package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.BookController;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.OrderDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.OrderDAOImpl;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderDetail;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderLog;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;

public class TestOrderDAO {
	private static OrderLog orderLog1 = null;
	private static OrderLog orderLog2 = null;
	static {	// initialization
		List<OrderDetail> orderDetails1 = new ArrayList<>();
		orderDetails1.add(new OrderDetail(3,1));
		orderDetails1.add(new OrderDetail(5,2));
		orderDetails1.add(new OrderDetail(3,2));
		orderLog1 = new OrderLog(3, 790, orderDetails1);
		
		List<OrderDetail> orderDetails2 = new ArrayList<>();
		orderDetails2.add(new OrderDetail(4,2));
		orderDetails2.add(new OrderDetail(3,3));
		orderDetails2.add(new OrderDetail(1,1));
		orderLog2 = new OrderLog(3, 790, orderDetails1);
	}
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		OrderDAO orderDAO = ctx.getBean("orderDAOImpl", OrderDAOImpl.class);
		/*
		// test insert a order log
		List<OrderLog> orderLogs = new ArrayList<OrderLog>();
		orderLogs.add(orderLog1);
		orderLogs.add(orderLog2);
		System.out.println(orderLogs);
		Integer result;
		try {
			result = orderDAO.insertLogWithDetails(orderLogs);
			System.out.println(result);
		} catch (DAOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		*/
		

		// query all
		System.out.println("query all");

		List<OrderLog> orderLogs;
		try {
			orderLogs = orderDAO.queryAllLog();
			orderLogs.forEach(o->System.out.println(o));
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// query by id
		System.out.println("query by id");
		Integer id = 8;
		try {
			OrderLog orderLog = orderDAO.queryLogById(id);
			System.out.println(orderLog);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// query by wid
		System.out.println("query by wid");
		Integer wid = 2;
		try {
			List<OrderLog> orderLogsWid = orderDAO.queryLogByWid(wid);
			orderLogsWid.forEach(o->System.out.println(o));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/*
		System.out.println("insert order log only");
		OrderLog ol1 = new OrderLog(2,100);
		OrderLog ol2 = new OrderLog(3,200);
		OrderLog ol3 = new OrderLog(4,400);
		try {
			List<Integer> re = orderDAO.insertLog(ol1,ol2,ol3);
			System.out.println(re);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		*/
	}
}
