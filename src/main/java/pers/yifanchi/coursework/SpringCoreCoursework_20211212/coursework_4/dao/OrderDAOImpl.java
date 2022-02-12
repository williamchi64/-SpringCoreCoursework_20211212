package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.annotation.FilterParam;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderDetail;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.OrderLog;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.utils.CustomJDBCTemplate;

@Repository
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CustomJDBCTemplate cJdbcTemplate;

	private String logDetailSql = 
			"select ol.id, ol.wid, ol.money, ol.create_time, "
			+ "od.id as order_details_id, od.oid as order_details_oid, "
			+ "od.bid as order_details_bid, od.amount as order_details_amount "
			+ "from orderlog ol "
			+ "left join orderdetail od "
			+ "on ol.id = od.oid ";
	@Override
	@FilterParam("Incorrect dao query all logs")
	public List<OrderLog> queryAllLog() throws Exception {
		String sql = logDetailSql;
		ResultSetExtractor<List<OrderLog>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("id").newResultSetExtractor(OrderLog.class);
		List<OrderLog> orderLogs = jdbcTemplate.query(sql, resultSetExtractor);
		return orderLogs.size()==0?null:orderLogs;
	}
	@Override
	@FilterParam(value = "Incorrect order log id, id [%s] could be wrong!", isFormat = true)
	public OrderLog queryLogById(Integer id) throws Exception {
		String sql = logDetailSql + "where ol.id = ?";
		ResultSetExtractor<List<OrderLog>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("id").newResultSetExtractor(OrderLog.class);
		List<OrderLog> orderLogs = jdbcTemplate.query(sql, resultSetExtractor, id);
		return orderLogs.size()==0?null:orderLogs.get(0);
	}
	@Override
	@FilterParam(value = "Incorrect wallet id, id [%s] could be wrong!", isFormat = true)
	public List<OrderLog> queryLogByWid(Integer wid) throws Exception {
		String sql = logDetailSql + "where ol.wid = ?";
		ResultSetExtractor<List<OrderLog>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("id").newResultSetExtractor(OrderLog.class);
		List<OrderLog> orderLogs = jdbcTemplate.query(sql, resultSetExtractor, wid);
		return orderLogs.size()==0?null:orderLogs;
	}
	@Override
	@FilterParam(value = "Incorrect insertion on order log [%s]", isFormat = true)
	public Integer insertLogWithDetails(OrderLog orderLog) throws Exception {
		List<OrderLog> orderLogs = new ArrayList<>();
		orderLogs.add(orderLog);
		return this.insertLogWithDetails(orderLogs);
	}
	@Override
	@FilterParam(value = "Incorrect insertion on order log [%s]", isFormat = true)
	public Integer insertLogWithDetails(List<OrderLog> orderLogs) throws Exception {
		List<Integer> oids = this.insertLog(orderLogs);
		List<Integer> results = new ArrayList<>();
		for (int i=0; i<oids.size(); i++) {
			Integer result = this.insertDetailsByLogId(oids.get(i), orderLogs.get(i).getOrderDetails());
			results.add(result);
		}
		return results.stream().anyMatch(result->result==0)?null:1;
	}
	@Override
	@FilterParam(value = "Incorrect insertion on order log part, log [%s]", isFormat = true)
	public List<Integer> insertLog(List<OrderLog> orderLogs) throws Exception {
		String sql = "insert into orderlog(wid, money) values (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, orderLogs.get(i).getWid());
				ps.setInt(2, orderLogs.get(i).getMoney());
			}
			@Override
			public int getBatchSize() {
				return orderLogs.size();
			}
		};
		int[] results = cJdbcTemplate.batchUpdatet(sql, batchPreparedStatementSetter, keyHolder);
		boolean checkResult = Arrays.stream(results).anyMatch(result->result==0);
		if (checkResult)
			throw new DAOException(String.format("Incorrect insertion on order log part, log [%s]", orderLogs.toString()));
		List<Integer> keys = keyHolder.getKeyList().stream()
				.map(key->(Integer)((BigInteger)key.get("GENERATED_KEY")).intValueExact())
				.collect(Collectors.toList());
		return keys.stream().anyMatch(key->key==null)?null:keys;
	}
	@Override
	@FilterParam(value = "Incorrect insertion on order log id [%s], order details [%s]", isFormat = true)
	public Integer insertDetailsByLogId(Integer oid, List<OrderDetail> orderDetails) throws Exception {
		String sql = "insert into orderdetail(oid, bid, amount) values (?, ?, ?)";
		BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, oid);
				ps.setInt(2, orderDetails.get(i).getBid());
				ps.setInt(3, orderDetails.get(i).getAmount());
			}
			@Override
			public int getBatchSize() {
				return orderDetails.size();
			}
		};
		int[] results = jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetter);
		return Arrays.stream(results).anyMatch(result->result==0)?null:1;
	}

}
