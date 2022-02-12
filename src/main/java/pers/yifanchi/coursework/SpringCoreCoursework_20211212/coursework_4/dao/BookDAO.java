package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import java.util.List;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Book;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.utils.Page;

public interface BookDAO {
	Integer queryBookCount() throws Exception;
	List<Book> queryBookStocksByPage(Page page) throws Exception;
	List<Book> queryBookStocksByIds(List<Integer> bids) throws Exception;
	Integer updateWalletMoney(Wallet wallet) throws Exception;
}
