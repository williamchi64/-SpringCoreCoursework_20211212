package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import java.util.List;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Book;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Stock;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;

public interface BookDAO {
	List<Book> queryBookStocksByIds(List<Integer> bids) throws DAOException;
	Wallet queryWalletById(Integer wid) throws DAOException;
	Integer updateStock(Stock stock) throws DAOException;
	Integer updateWalletMoney(Wallet wallet) throws DAOException;
}
