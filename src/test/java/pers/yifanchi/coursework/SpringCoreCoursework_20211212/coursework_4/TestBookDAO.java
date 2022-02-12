package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.BookDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.BookDAOImpl;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Book;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.utils.Page;

public class TestBookDAO {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		BookDAO bookDAO = ctx.getBean("bookDAOImpl", BookDAOImpl.class);
		/*
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);

		List<Book> bs;
		try {
			bs = bookDAO.queryBookStocksByIds(ids);
			System.out.println(bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		/*
		try {
			List<Book> books = bookDAO.queryBookStocksByPage(new Page(0,5));
			System.out.println(books);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
		try {
			Integer count = bookDAO.queryBookCount();
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
