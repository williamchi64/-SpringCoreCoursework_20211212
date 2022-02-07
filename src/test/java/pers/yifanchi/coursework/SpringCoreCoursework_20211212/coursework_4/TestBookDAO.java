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

public class TestBookDAO {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		BookDAO bookDAO = ctx.getBean("bookDAOImpl", BookDAOImpl.class);
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		try {
			List<Book> bs = bookDAO.queryBookStocksByIds(ids);
			System.out.println(bs);
		} catch (DAOException e) {
			System.out.println(e.getMessage());
		}
//		Integer id = 1;
		Integer id = 6;
		try {
			Wallet w = bookDAO.queryWalletById(id);
			System.out.println(w);
		} catch (DAOException e) {
			System.out.println(e.getMessage());
		}
	}
}
