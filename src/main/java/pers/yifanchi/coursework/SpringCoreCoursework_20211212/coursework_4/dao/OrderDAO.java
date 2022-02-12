package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import java.util.List;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderDetail;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderLog;

public interface OrderDAO {
	List<OrderLog> queryAllLog() throws Exception;
	OrderLog queryLogById(Integer id) throws Exception;
	List<OrderLog> queryLogByWid(Integer wid) throws Exception;
	Integer insertLogWithDetails(OrderLog orderLog) throws Exception;
	Integer insertLogWithDetails(List<OrderLog> orderLogs) throws Exception;
	List<Integer> insertLog(List<OrderLog> orderLogs) throws Exception;
	Integer insertDetailsByLogId(Integer oid, List<OrderDetail> orderDetails) throws Exception;
}
