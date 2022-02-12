package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.BookController;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.OrderController;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.BookDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.BookDAOImpl;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.BookPrintDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;

public class TestBookController {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		BookController bookController = ctx.getBean("bookController",BookController.class);
		List<BookPrintDTO> bookPrintDTOs = bookController.getBookList(1);
		System.out.println(bookPrintDTOs);
	}

}
