package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.BookController;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.OrderController;

@Component
public class BookOrderSystem {
	private boolean stop = true;
//	private ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
//	private BookController bookController = ctx.getBean("bookController", BookController.class);
	
	@Autowired
	private BookController bookController;
	@Autowired
	private OrderController orderController;
	
	private void menu() {
		String menu
			= "---------------------\n"
			+ "1. 購買書籍\n"
			+ "2. 查看購物記錄\n"
			+ "0. 離開\n"
			+ "---------------------\n";
		System.out.println(menu);
		Scanner sc = new Scanner(System.in);
		String choice = sc.next();
		switch (choice) {
		case "0":
			System.out.println("系統關閉");
			stop = false;
			break;
		case "1":
			buyBooks(sc, getAuth(sc));
			break;
		case "2":
			printOrderLogs(sc, getAuth(sc));
			break;
		default:
			System.out.println("請輸入正確數字");
			break;
		}
	}
	private Integer getAuth(Scanner sc) {
		System.out.println("請輸入您的賬號");
		Integer wid = null;
		while (true) {
			try {
				System.out.println("賬號號碼");
				wid = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("請重新輸入正確數字");
				sc.next();
				continue;
			}
		}
		return wid;
		
	}
	
	private void buyBooks(Scanner sc, Integer wid) {
		System.out.println("請輸入書號與數量");
		Map<Integer, Integer> bookOrder = new HashMap<>();
		while (true) {
			Integer bid = null;
			Integer amount = null;
			try {
				System.out.println("請輸入書號");
				bid = sc.nextInt();
				System.out.println("請輸入個數");
				amount = sc.nextInt();
				bookOrder.put(bid, amount);
				System.out.println("輸入y繼續增加購買書籍，輸入其他完成購買");
				String check = sc.next();
				if(!check.equals("y"))
					break;
			} catch (Exception e) {
				System.out.println("請重新輸入正確數字");
				sc.next();
				continue;
			}
		}
		bookController.buyBook(wid, bookOrder);
	}
	
	private void printOrderLogs(Scanner sc, Integer wid) {
		System.out.println("您的購物記錄");

		String orderLog = orderController.getLogsById(wid).stream().map(l->{
			StringBuilder sb = new StringBuilder();
			sb.append(l.getName()+" 在 ");
			sb.append(l.getCreatetime()+" 花費 ");
			sb.append(l.getTotalCost()+" 買了\n");
			String s = l.getBookNameMap().keySet().stream().map(
				key->
					l.getBookNameMap().get(key)+" 共 "+
					l.getBookAmountMap().get(key)+" 本\n"
			).collect(Collectors.joining());
			sb.append(s);
			return sb.toString();
		}).collect(Collectors.joining());
		
		System.out.println(orderLog);
	}

	public void start() {
		while (stop) {
			menu();
		}
	}

}
