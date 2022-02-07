package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.enums.LogType;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.EmpDAO;
/* homework
每次查詢中，都可以將查詢時間的log記錄下來
+-------------+---------------------+
| method_name |    log_timestamp    |
+-------------+---------------------+
|  queryAll   | 2022/01/10 13:50:43 |
+-------------+---------------------+
*/
public class TestEmpDAO {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		EmpDAO empDAO = ctx.getBean("empDAO", EmpDAO.class);
		empDAO.queryAll().forEach(System.out::println);
	}
}
