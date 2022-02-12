package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service;

import java.util.List;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.LogPrintDTO;

public interface OrderService {
	List<LogPrintDTO> queryAllOrderLogs() throws Exception;
	List<LogPrintDTO> queryOrderLogsByWid(Integer wid) throws Exception;
}
