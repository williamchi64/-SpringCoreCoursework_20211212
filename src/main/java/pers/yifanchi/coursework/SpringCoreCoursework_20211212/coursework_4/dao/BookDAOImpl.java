package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import java.util.Collections;
import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.annotation.FilterParam;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Book;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Stock;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@FilterParam(value = "Incorrect book id, one of ids [%s] could be wrong! ", isFormat = true)
	public List<Book> queryBookStocksByIds(List<Integer> bids) throws DAOException {
		String headSQL = "select b.bid, b.bname, b.price, b.create_time, "
				+ "s.sid as stock_sid, s.bid as stock_bid, s.amount as stock_amount "
				+ "from book as b "
				+ "left join stock as s "
				+ "on b.bid = s.bid "
				+ "where b.bid in (";
		String symbol = this.duplicateSymbols(bids.size());
		String endSQL = ")";
		String sql = headSQL + symbol + endSQL;
		ResultSetExtractor<List<Book>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("bid").newResultSetExtractor(Book.class);
		List<Book> books = jdbcTemplate.query(sql, resultSetExtractor, bids.toArray());
		return books.size()==0?null:books;
	}
	@Override
	@FilterParam(value = "Incorrect wallet id, id [%s] could be wrong! ", isFormat = true)
	public Wallet queryWalletById(Integer wid) throws DAOException {
		String sql = "select wid, wname, money from wallet "
				+ "where wid = ?";
		List<Wallet> wallets = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Wallet>(Wallet.class), wid);
		return wallets.size()==0?null:wallets.get(0);
	}
	@Override
	@FilterParam(value = "Incorrect book, book [%s] could be wrong! ", isFormat = true)
	public Integer updateStock(Stock stock) throws DAOException {
		String sql = "update stock set amount = ? where bid = ?";
		int result = jdbcTemplate.update(sql, stock.getAmount(), stock.getBid());
		return result==0?null:result;
	}
	@Override
	@FilterParam(value = "Incorrect wallet, wallet [%s] could be wrong! ", isFormat = true)
	public Integer updateWalletMoney(Wallet wallet) throws DAOException {
		String sql = "update wallet set money = ? where wid = ?";
		int result = jdbcTemplate.update(sql, wallet.getMoney(), wallet.getWid());
		return result==0?null:result;
	}

	private String duplicateSymbols(int batch) {
		return String.join(", ", Collections.nCopies(batch, "?"));
	}
}
