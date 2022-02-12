package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import java.util.Collections;
import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.annotation.FilterParam;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Book;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.utils.Page;

@Repository
public class BookDAOImpl implements BookDAO {
	
	private final static String QUERYBOOKSTOCKSQL = 
			"select b.bid, b.bname, b.price, b.create_time, "
			+ "s.sid as stock_sid, s.bid as stock_bid, s.amount as stock_amount "
			+ "from book as b "
			+ "left join stock as s "
			+ "on b.bid = s.bid ";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer queryBookCount() throws Exception {
		String sql = "select COUNT(bid) from book";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Override
	public List<Book> queryBookStocksByPage(Page page) throws Exception {
		String sql = QUERYBOOKSTOCKSQL + "limit ?, ?";
		ResultSetExtractor<List<Book>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("bid").newResultSetExtractor(Book.class);
		List<Book> books = jdbcTemplate.query(sql, resultSetExtractor, page.getStart(), page.getTotal());
		return books.size()==0?null:books;
	}

	@Override
	@FilterParam(value = "Incorrect book id, one of ids [%s] could be wrong! ", isFormat = true)
	public List<Book> queryBookStocksByIds(List<Integer> bids) throws Exception {
		String headSQL = QUERYBOOKSTOCKSQL + "where b.bid in (";
		String symbol = this.duplicateSymbols(bids.size());
		String endSQL = ")";
		String sql = headSQL + symbol + endSQL;
		ResultSetExtractor<List<Book>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("bid").newResultSetExtractor(Book.class);
		List<Book> books = jdbcTemplate.query(sql, resultSetExtractor, bids.toArray());
		return books.size()==0?null:books;
	}
	@Override
	@FilterParam(value = "Incorrect wallet, wallet [%s] could be wrong! ", isFormat = true)
	public Integer updateWalletMoney(Wallet wallet) throws Exception {
		String sql = "update wallet set money = ? where wid = ?";
		int result = jdbcTemplate.update(sql, wallet.getMoney(), wallet.getWid());
		return result==0?null:result;
	}

	private String duplicateSymbols(int batch) {
		return String.join(", ", Collections.nCopies(batch, "?"));
	}



}
