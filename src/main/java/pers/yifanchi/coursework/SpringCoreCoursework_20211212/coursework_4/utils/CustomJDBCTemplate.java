package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.sql.Statement;

@Component
public class CustomJDBCTemplate extends JdbcTemplate {
	public int[] batchUpdatet(
		final String sql,
		final BatchPreparedStatementSetter pss, 
		final KeyHolder generatedKeyHolder
	) throws DataAccessException {
		PreparedStatementCreator pscr = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		};
		PreparedStatementCallback<int[]> pscb = new PreparedStatementCallback<int[]>() {
			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException {
				if (logger.isDebugEnabled())
					logger.debug("Executing batch SQL update and returning " +
					"generated keys [" + sql + "]");
				try {
					int batchSize = pss.getBatchSize();
					int totalRowsAffected = 0;
					int[] rowsAffected = new int[batchSize];
					List<Map<String, Object>> generatedKeys = generatedKeyHolder.getKeyList();
					generatedKeys.clear();
					ResultSet keys = null;
					for (int i = 0; i < batchSize; i++) {
						pss.setValues(ps, i);
						rowsAffected[i] = ps.executeUpdate();
						totalRowsAffected += rowsAffected[i];
						try {
							keys = ps.getGeneratedKeys();
							if (keys != null) {
								RowMapperResultSetExtractor<Map<String, Object>> rse =
										new RowMapperResultSetExtractor<Map<String, Object>>(getColumnMapRowMapper(), 1);
								generatedKeys.addAll(rse.extractData(keys));
							}
						} finally {
							JdbcUtils.closeResultSet(keys);
						}
					}
					if (logger.isDebugEnabled())
						logger.debug("SQL batch update affected "
								+ totalRowsAffected + " rows and returned "
								+ generatedKeys.size() + " keys");
					return rowsAffected;
				} finally {
					if (pss instanceof ParameterDisposer)
						((ParameterDisposer) pss).cleanupParameters();
				}
			}
		};
		return execute(pscr,pscb);
	}
}
