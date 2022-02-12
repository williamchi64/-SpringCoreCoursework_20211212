package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.BookDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.OrderDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.WalletDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.LogPrintDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Book;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderDetail;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderLog;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private BookDAO bookDAO;
	@Autowired
	private WalletDAO walletDAO;
	@Override
	public List<LogPrintDTO> queryAllOrderLogs() throws Exception {
		List<OrderLog> orderLogs = orderDAO.queryAllLog();
		return this.generateLogPrintDTO(orderLogs);
	}
	@Override
	public List<LogPrintDTO> queryOrderLogsByWid(Integer wid) throws Exception {
		List<OrderLog> orderLogs = orderDAO.queryLogByWid(wid);
		return this.generateLogPrintDTO(orderLogs);
	}
	private List<LogPrintDTO> generateLogPrintDTO(List<OrderLog> orderLogs) throws Exception {
		List<LogPrintDTO> logPrintDTOs = new ArrayList<>();
		for (OrderLog orderLog: orderLogs) {
			Integer wid = orderLog.getWid();
			Date createtime = orderLog.getCreateTime();
			Integer totalCost = orderLog.getMoney();
			List<OrderDetail> orderDetails = orderLog.getOrderDetails();

			String name = walletDAO.queryWalletById(wid).getWname();
			Map<Integer, Integer> bookAmountMap = orderDetails.stream().collect(Collectors.toMap(o->o.getBid(), o->o.getAmount()));
			List<Integer> bids = bookAmountMap.keySet().stream().collect(Collectors.toList());
			List<Book> books = bookDAO.queryBookStocksByIds(bids);
			Map<Integer, String> bookNameMap = books.stream().collect(Collectors.toMap(b->b.getBid(), b->b.getBname()));
			LogPrintDTO logPrintDTO = new LogPrintDTO(name, createtime, totalCost, bookNameMap, bookAmountMap);
			logPrintDTOs.add(logPrintDTO);
		}
		return logPrintDTOs;
	}
}
