package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.LogPrintDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	public List<LogPrintDTO> getAllLogs() {
		List<LogPrintDTO> logPrintDTOs = null;
		try {
			logPrintDTOs = orderService.queryAllOrderLogs();
		} catch (Exception e) {}
		return logPrintDTOs;
	}
	
	public List<LogPrintDTO> getLogsById(Integer wid) {
		List<LogPrintDTO> logPrintDTOs = null;
		try {
			logPrintDTOs = orderService.queryOrderLogsByWid(wid);
		} catch (Exception e) {}
		return logPrintDTOs;
	}

}
