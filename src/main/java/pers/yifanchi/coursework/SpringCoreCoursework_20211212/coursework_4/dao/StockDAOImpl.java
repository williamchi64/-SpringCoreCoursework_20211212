package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.annotation.FilterParam;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Stock;

@Repository
public class StockDAOImpl implements StockDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@FilterParam(value = "Incorrect book, book [%s] could be wrong! ", isFormat = true)
	public Integer updateStock(Stock stock) throws Exception {
		String sql = "update stock set amount = ? where bid = ?";
		int result = jdbcTemplate.update(sql, stock.getAmount(), stock.getBid());
		return result==0?null:result;
	}
}
