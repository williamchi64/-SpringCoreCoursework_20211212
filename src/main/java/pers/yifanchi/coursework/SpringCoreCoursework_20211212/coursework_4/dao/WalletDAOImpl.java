package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.annotation.FilterParam;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;

@Repository
public class WalletDAOImpl implements WalletDAO {
	private final static String queryWalletSQL = "select wid, wname, money from wallet ";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	@FilterParam(value = "Incorrect wallet id, id [%s] could be wrong! ", isFormat = true)
	public Wallet queryWalletById(Integer wid) throws Exception {
		String sql = queryWalletSQL + "where wid = ?";
		List<Wallet> wallets = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Wallet>(Wallet.class), wid);
		return wallets.size()==0?null:wallets.get(0);
	}
	@Override
	@FilterParam(value = "", disableNullFilter = true)
	public Wallet queryWalletByName(String wname) throws Exception {
		String sql = queryWalletSQL + "where wname = ?";
		List<Wallet> wallets = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Wallet>(Wallet.class), wname);
		return wallets.size()==0?null:wallets.get(0);
	}
	@Override
	public Integer insertWalletGetWid(Wallet wallet) throws Exception {
		String sql = "insert into wallet(`wname`, `money`) values (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, wallet.getWname());
				ps.setInt(2, wallet.getMoney());
				return ps;
			}
		};
		int result = jdbcTemplate.update(preparedStatementCreator, keyHolder);
		if (result==0)
			throw new DAOException(String.format(
				"incorrect insertion on wallet, name %s, money %s",
				wallet.getWname(), wallet.getMoney()
			));
		return keyHolder.getKey().intValue();
	}
}
