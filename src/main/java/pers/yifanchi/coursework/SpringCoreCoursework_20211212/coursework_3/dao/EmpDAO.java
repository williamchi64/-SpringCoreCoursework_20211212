package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Emp;

@Repository
public class EmpDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private ComboPooledDataSource dataSource;
	
	// 多筆查詢
	public List<Map<String, Object>> queryAll() {
		String sql = "select eid, ename, age, createtime from emp";
		List<Map<String, Object>> emps = jdbcTemplate.queryForList(sql);
		return emps;
	}
	// 多筆查詢 2
	public List<Emp> queryListEmp1() {
		String sql = "select eid, ename, age, createtime from emp";
		List<Emp> emps = jdbcTemplate.query(
				sql,
				(ResultSet rs, int rowNum) -> {
					Emp emp = new Emp();
					emp.setEid(rs.getInt("eid"));
					emp.setEname(rs.getString("ename"));
					emp.setAge(rs.getInt("age"));
					emp.setCreatetime(rs.getTimestamp("createtime"));
					return emp;
				}
		);
		return emps;
	}
	// 多筆查詢 3
	public List<Emp> queryListEmp2() {
		String sql = "select eid, ename, age, createtime from emp";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
	}
	// 新增 1
	public int addOne1(String ename, Integer age) {
		String sql = "insert into emp(ename, age) values(?,?)";
		int rowCount = jdbcTemplate.update(sql, ename, age);
		return rowCount;
	}
	// 新增 2
	public int addOne2(String ename, Integer age) {
		String sql = "insert into emp(ename, age) values(:ename,:age)";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("ename", ename)
				.addValue("age", age);
		int rowCount = namedParameterJdbcTemplate.update(sql, params);
		return rowCount;
	}
	// 多筆新增 1
	public int[] multiAdd1(List<Object[]> rows) {
		String sql = "insert into emp(ename, age) values(?,?)";
		return jdbcTemplate.batchUpdate(sql, rows);
	}
	// 多筆新增 2
	public int[] multiAdd2(List<Emp> emps) {
		String sql = "insert into emp(ename, age) values(?,?)";
		BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, emps.get(i).getEname());
				ps.setInt(2, emps.get(i).getAge());
			}
			
			@Override
			public int getBatchSize() {
				return emps.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetter);
	}
	// 修改
	public int updateById(Integer eid, String ename, Integer age) {
		String sql = "update emp set ename=?, age=? where eid=?";
		return jdbcTemplate.update(sql, ename, age, eid);
	}
	// 刪除
	public int deleteById(Integer eid) {
		String sql = "delete from emp where eid=?";
		return jdbcTemplate.update(sql, eid);
	}
	
	// transaction
	public int addOneTx(String ename, Integer age) throws Exception {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		int rowCount = 0;
		try {
			String sql = "insert into emp(ename, age) values(?,?)";
			rowCount = jdbcTemplate.update(sql, ename, age);
//			System.out.println(10/0);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
		transactionManager.commit(status);
		return rowCount;
	}
}
