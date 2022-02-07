package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonSystem {
	/**
	 * homework 2
	 */
	// 
	static ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/applicationContext2.xml");
	static PersonController personController = ctx.getBean("personController", PersonController.class);
	private static boolean stop = true;
	// view of system 
	private void menu() {
		System.out.println("---------------------");
		System.out.println("1. 建立Person資料");
		System.out.println("2. 取得Person資料");
		System.out.println("3. 根據姓名取得 Person");
		System.out.println("4. 取得今天生日的 Person");
		System.out.println("5. 取得某一歲數以上的 Person");
		System.out.println("6. 根據姓名修改 Person 生日（只修改重名的第一個）");
		System.out.println("7. 根據姓名刪除 Person（只修改重名的第一個）");
		System.out.println("0. 離開");
		System.out.println("---------------------");
		System.out.println("請選擇: ");
		Scanner sc = new Scanner(System.in);
		String choice = sc.next();
		switch (choice) {
		case "0":
			stop = false;
			System.out.println("系統關閉");
			break;
		case "1": createPerson(sc);
			break;
		case "2": printResult(viewAllPeople());
			break;
		case "3": printResult(viewPeopleByName(sc));
			break;
		case "4": printResult(viewPeopleByTodayDate());
			break;
		case "5": printResult(viewPeopleByAgeLarger(sc));
			break;
		case "6": updateDateByName(sc);
			break;
		case "7": deletePersonByName(sc);
			break;
		default:
			System.out.println("請輸入正確數字");
			break;
		}
	}
	// function
	// 1
	private void createPerson(Scanner sc) {
		int yyyy = 0;
		int mm = 0;
		int dd = 0;
		System.out.println("請輸入姓名 生日年 生日月 生日日");
		String name = sc.next();
		try {
			yyyy = sc.nextInt();
			mm = sc.nextInt();
			dd = sc.nextInt();
		} catch (Exception e) {
			System.out.println("錯誤輸入，請輸入正確生日年 生日月 生日日");
			menu();
		}
		personController.insertPerson(name, yyyy, mm, dd);
	}
	// 2
	private String viewAllPeople() {
		return peopleToString(personController.findAllPeople());
	}
	// 3
	private String viewPeopleByName(Scanner sc) {
		System.out.println("請輸入姓名");
		String name = sc.next();
		return peopleToString(personController.findPeopleByName(name));
	}
	// 4
	private String viewPeopleByTodayDate() {
		return peopleToString(personController.findPeopleByTodayDate());
	}
	// 5
	private String viewPeopleByAgeLarger(Scanner sc) {
		System.out.println("請輸入年齡");
		int age = sc.nextInt();
		return peopleToString(personController.findPeopleByAgeLarger(age));
	}
	// 6
	private void updateDateByName(Scanner sc) {
		System.out.println("請輸入名字");
		String name = sc.next();
		System.out.println("和需要修改的生日年 生日月 生日日");
		int yyyy = 0;
		int mm = 0;
		int dd = 0;
		try {
			yyyy = sc.nextInt();
			mm = sc.nextInt();
			dd = sc.nextInt();
		} catch (Exception e) {
			System.out.println("錯誤輸入，請輸入正確生日年 生日月 生日日");
			menu();
		}
		personController.updateDateByName(name, yyyy, mm, dd);
	}
	// 7
	private void deletePersonByName(Scanner sc) {
		System.out.println("請輸入名字");
		String name = sc.next();
		personController.deletePersonByName(name);
	}
	// print of result get from application
	private void printResult(String result) {
		System.out.println("+--------------+---------+--------------+");
		System.out.println("|     name     |   age   |   birthday   |");
		System.out.println("+--------------+---------+--------------+");
		System.out.println(result);
	}
	// convert person/people to viewable string
	private String personToString(Person p) {
		String birthday = new SimpleDateFormat("yyyy/MM/dd").format(p.getBirth());
		return String.format("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday)
				+ "+--------------+---------+--------------+";
	}
	private String peopleToString(List<Person> people) {
		StringBuffer sb = new StringBuffer();
		for (Person person: people) {
			sb.append(personToString(person) + "\n"); 
		}
		return sb.toString();
	}
	// system runtime logic
	public void start() {
		while (stop) {
			menu();
		}
	}

}
