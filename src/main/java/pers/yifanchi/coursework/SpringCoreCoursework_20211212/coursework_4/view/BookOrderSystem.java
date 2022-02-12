package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.BookController;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.OrderController;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.WalletController;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.BookPrintDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.LogPrintDTO;

@Component
public class BookOrderSystem {
	private boolean stop = true;
	private Integer wid = 0;
	Scanner sc = new Scanner(System.in);
	
	@Autowired
	private BookController bookController;
	@Autowired
	private OrderController orderController;
	@Autowired
	private WalletController walletController;
	
	private void menu() {
		String menu
			= "---------------------\n"
			+ "1. 創建賬號\n"
			+ "2. 查詢賬號號碼\n"
			+ "3. 查詢書籍\n"
			+ "4. 購買書籍\n"
			+ "5. 查看購物記錄\n"
			+ "0. 離開\n"
			+ "---------------------\n";
		System.out.println(menu);
		String choice = sc.next();
		switch (choice) {
		case "0":
			System.out.println("系統關閉");
			stop = false;
			break;
		case "1":
			createAcount(sc);
			break;
		case "2":
			getIdByName(sc);
			break;
		case "3":
			getBookByPage(sc);
			break;
		case "4":
			getAuth(sc);
			buyBooks(sc);
			break;
		case "5":
			getAuth(sc);
			printOrderLogs(sc);
			break;
		default:
			System.out.println("請輸入正確數字");
			break;
		}
	}
	private void getAuth(Scanner sc) {
		while (wid == 0) {
			try {
				System.out.println("請輸入您的賬號號碼，輸入0用賬號名查詢");
				wid = sc.nextInt();
				if (wid<0)
					throw new Exception();
				if (wid == 0)
					getIdByName(sc);
				break;
			} catch (Exception e) {
				System.out.println("請重新輸入大於等於零的數字");
				sc.next();
				continue;
			}
		}
	}
	
	private void createAcount(Scanner sc) {
		System.out.println("請輸入賬號名和初始金額");
		String wname =  null;
		Integer money = null;
		while(true) {
			try {
				System.out.println("請輸入賬號名");
				wname = sc.next();
				System.out.println("請輸入金額");
				money = sc.nextInt();
				wid = walletController.insertWallet(wname, money);
				if (wid != 0)
					System.out.println(String.format("請記住你的賬號號碼: %d", wid));
			} catch (Exception e) {
				System.out.println("請輸入正確的賬號名:字串和金額:整數");
				sc.next();
				continue;
			}
			break;
		}
	}
	
	private void getIdByName(Scanner sc) {
		String wname =  null;
		do {
			try {
				System.out.println("請輸入賬號名");
				wname = sc.next();
			} catch (Exception e) {
				System.out.println("請輸入正確的賬號名:字串");
				sc.next();
				continue;
			}
			wid = walletController.queryWidByName(wname);
			if (wid==0)
				System.out.println("沒有資料");
		} while(wid==0);
		System.out.println(String.format("請記住你的賬號號碼: %d", wid));
	}
	
	private void getBookByPage(Scanner sc) {
		System.out.println("每頁顯示5本");
		Integer pageNum = 1;
		while (true) {
			List<BookPrintDTO> bookPrintDTOs = bookController.getBookList(pageNum);
			if (bookPrintDTOs!=null) {
				bookPrintDTOs.forEach(bookPrintDTO->System.out.println(
					"書號:\t"+bookPrintDTO.getBid()+"\n"
					+"書名:\t"+bookPrintDTO.getBname()+"\n"
					+"書籍價格:\t"+bookPrintDTO.getPrice()+"\n"
					+"上架時間:\t"+bookPrintDTO.getCreateTime()+"\n"
					+"上架數量:\t"+bookPrintDTO.getAmount()
				));
			} else {
				System.out.println("已經是最後一頁了");
				pageNum--;
				continue;
			}
			
			try {
				System.out.println("輸入U打印上頁，輸入D打印下頁，輸入其他退出");
				String sign = sc.next();
				if (sign.equals("U") || sign.equals("u")) {
					if (pageNum<=1) {
						System.out.println("已經是最前一頁了");
						continue;
					}
					pageNum--;
				}else if (sign.equals("D") || sign.equals("d")) {
					pageNum++;
				} else {
					break;
				}
			} catch (Exception e) {
				sc.next();
				break;
			}
		}
	}
	
	private void buyBooks(Scanner sc) {
		System.out.println("請輸入書號和數量");
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
		Integer result = bookController.buyBook(wid, bookOrder);
		if (result!=0)
			bookOrder.keySet().forEach(key->
				System.out.println(
					"賬號 "+wid+" 成功購買: "
					+key+" 號書 "+bookOrder.get(key)+" 本"
				)
			);
			
	}
	
	private void printOrderLogs(Scanner sc) {
		System.out.println("您的購物記錄");
		List<LogPrintDTO> logPrintDTOs = orderController.getLogsById(wid);
		if (logPrintDTOs!=null) {
			String orderLog = logPrintDTOs.stream().map(l->{
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
		} else {
			System.out.println(String.format("賬號 %d 沒有訂書紀錄", wid));
		}
		
	}

	public void start() {
		while (stop) {
			menu();
		}
	}

}
