package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Log;

@Repository
public class LogDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Long insertLog(Log log) {
		String sql = "insert into log (log_type, log_state, method_name, log_start_timestamp, " +
				"log_end_timestamp, description) values (?, ?, ?, ?, ?, ?);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preparedStatement = con.prepareStatement(sql, new String[] {"lid"});
				preparedStatement.setString(1, log.getLogType().toString());
				preparedStatement.setString(2, log.getLogState().toString());
				preparedStatement.setString(3, log.getMethodName());
				preparedStatement.setTimestamp(4, new Timestamp(log.getLogStartTimestamp().getTime()));
				preparedStatement.setTimestamp(5, new Timestamp(log.getLogStartTimestamp().getTime()));
				preparedStatement.setString(6, log.getDescription());
				return preparedStatement;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}
}
