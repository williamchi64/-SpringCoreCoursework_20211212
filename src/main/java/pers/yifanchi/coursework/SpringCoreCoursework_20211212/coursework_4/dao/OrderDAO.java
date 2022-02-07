package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import java.util.List;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderDetail;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderLog;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;

public interface OrderDAO {
	List<OrderLog> queryAllLog() throws DAOException;
	OrderLog queryLogById(Integer id) throws DAOException;
	List<OrderLog> queryLogByWid(Integer wid) throws DAOException;
	Integer insertLogWithDetails(OrderLog orderLog) throws DAOException;
	Integer insertLogWithDetails(List<OrderLog> orderLogs) throws DAOException;
	List<Integer> insertLog(List<OrderLog> orderLogs) throws DAOException;
	Integer insertDetailsByLogId(Integer oid, List<OrderDetail> orderDetails) throws DAOException;
}
