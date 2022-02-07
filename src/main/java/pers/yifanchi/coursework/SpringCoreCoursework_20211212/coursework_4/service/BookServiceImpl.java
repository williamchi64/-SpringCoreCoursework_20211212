package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.BookDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.OrderDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto.BookTransactDTO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Book;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderDetail;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderLog;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Stock;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.ServiceException;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDAO bookDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Transactional(
		propagation = Propagation.REQUIRED,
		rollbackFor = {DAOException.class, ServiceException.class}
	)
	// getConnetion(), setAutoCommit(false), commit()
	@Override
	public Integer booksTransact(BookTransactDTO bookTransactDTO) throws DAOException, ServiceException {
		BookTransactDTO bookWithPrice = this.getBookPriceStock(bookTransactDTO);
		BookTransactDTO bookAfterTransact = this.updateWalletStock(bookWithPrice);
		
		Integer wid = bookAfterTransact.getWid();
		Integer totalCost = bookAfterTransact.getTotalPrice();
		Map<Integer, Integer> bookAmountMap = bookAfterTransact.getBookAmountMap();

		List<OrderDetail> orderDetails = bookAmountMap.keySet().stream().map(
			key -> new OrderDetail(key, bookAmountMap.get(key))
		).collect(Collectors.toList());
		return orderDAO.insertLogWithDetails(new OrderLog(wid, totalCost, orderDetails));
	}

	public BookTransactDTO getBookPriceStock(BookTransactDTO bookTransactDTO) throws DAOException {
		Map<Integer, Integer> bookAmountMap = bookTransactDTO.getBookAmountMap();
		List<Integer> bids = bookAmountMap.keySet().stream().collect(Collectors.toList());
		List<Book> books = bookDAO.queryBookStocksByIds(bids);
		Map<Integer, Integer[]> bookPriceStockMap = books.stream()
			.collect(
				Collectors.toMap(
					Book::getBid, 
					book -> new Integer[]{
						book.getPrice() * bookAmountMap.get(book.getBid()), 
						book.getStock().getAmount()
					}
				)
			);
		bookTransactDTO.setBookPriceStockMap(bookPriceStockMap);
		return bookTransactDTO;
	}

	public BookTransactDTO updateWalletStock(BookTransactDTO bookTransactDTO) throws DAOException, ServiceException {
		Map<Integer, Integer> bookAmountMap = bookTransactDTO.getBookAmountMap();
		Map<Integer, Integer[]> bookPriceStockMap = bookTransactDTO.getBookPriceStockMap();
		Integer totalCost = 0;
		for (Integer bid: bookPriceStockMap.keySet()) {
			Integer[] arr = bookPriceStockMap.get(bid);
			Integer unitTotalPrice = arr[0];
			Integer stock = arr[1];
			Integer amount = bookAmountMap.get(bid);
			if (amount > stock) 
				throw new ServiceException(String.format("Insufficient book amount asking, book id [%s]", bid.toString()));
			bookDAO.updateStock(new Stock(bid, stock - amount));
			totalCost += unitTotalPrice;
		}

		Integer wid = bookTransactDTO.getWid();
		Wallet wallet = bookDAO.queryWalletById(wid);
		Integer money = wallet.getMoney();
		if (money < totalCost)
			throw new ServiceException(String.format("Insufficient wallet credit, wallet id [%s]", wid.toString()));
		wallet.setMoney(money - totalCost);
		bookDAO.updateWalletMoney(wallet);
		bookTransactDTO.setTotalPrice(totalCost);
		return bookTransactDTO;
	}
}
