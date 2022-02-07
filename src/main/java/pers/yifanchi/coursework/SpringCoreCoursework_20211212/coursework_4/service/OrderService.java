package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service;

import java.util.List;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.LogPrintDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.ServiceException;

public interface OrderService {
	List<LogPrintDTO> queryAllOrderLogs() throws DAOException, ServiceException;
	List<LogPrintDTO> queryOrderLogsByWid(Integer wid) throws DAOException, ServiceException;
}
