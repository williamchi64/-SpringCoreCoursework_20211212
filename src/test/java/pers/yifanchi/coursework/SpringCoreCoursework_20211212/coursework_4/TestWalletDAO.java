package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.WalletDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.WalletDAOImpl;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;

public class TestWalletDAO {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		WalletDAO walletDAO = ctx.getBean("walletDAOImpl", WalletDAOImpl.class);
		String wname = "ChiChiStudy";
		Integer money = 3000;
		Wallet wallet = new Wallet(wname, money);

		Integer key;
		try {
			key = walletDAO.insertWalletGetWid(wallet);
			System.out.println(key);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		((AbstractApplicationContext) ctx).close();
	}

}
