package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.BookController;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.BookDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.BookDAOImpl;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.BookTransactDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.ServiceException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.BookService;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.BookServiceImpl;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.WalletService;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.WalletServiceImpl;

public class TestWalletService {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		WalletService walletService = ctx.getBean("walletServiceImpl", WalletServiceImpl.class);
		/*
		String wname = "ChiChiStudy2";
		Integer money = 3000;
		Wallet wallet = new Wallet(wname, money);
		try {
			Integer key = walletService.insertWalletGetWid(wallet);
			System.out.println(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		String wname2 = "abdc";
		try {
			Wallet wallet = walletService.queryWalletByName(wname2);
			System.out.println(wallet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
