package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.BookController;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.BookDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.BookDAOImpl;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.BookTransactDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.ServiceException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.BookService;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.BookServiceImpl;

public class TestBookService {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		BookServiceImpl bookService = ctx.getBean("bookServiceImpl", BookServiceImpl.class);
		
		Map<Integer, Integer> bookAmountMap = new HashMap<>();
		bookAmountMap.put(1, 1);
		bookAmountMap.put(2, 3);
		bookAmountMap.put(3, 1);
		BookTransactDTO bt1 = new BookTransactDTO(3, bookAmountMap);
		/*
		System.out.println(bt1);
		try {
			BookTransactDTO bt2 = bookService.getBookPrice(bt1);
			System.out.println(bt2);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		*/
		try {
			bookService.booksTransact(bt1);
		} catch (ServiceException|DAOException e) {
			System.out.println(e.getMessage());
		}
	}

}
