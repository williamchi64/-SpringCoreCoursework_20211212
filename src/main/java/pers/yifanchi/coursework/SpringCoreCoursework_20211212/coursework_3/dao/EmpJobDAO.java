package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao;

import java.sql.ResultSet;
import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Emp;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Job;

@Repository
public class EmpJobDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Emp> queryEmpJobBySameId1() {
		String sql = "select eid, ename, age, createtime from emp";
		List<Emp> emps = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
			Emp emp = new Emp();
			// 設定欄位
			emp.setEid(rs.getInt("eid"));
			emp.setEname(rs.getString("ename"));
			emp.setAge(rs.getInt("age"));
			emp.setCreatetime(rs.getTimestamp("createtime"));
			// 通過獲得id查找job
			String sql2 = "select jid, jname, eid from job where eid=?";
			List<Job> jobs = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<Job>(Job.class), emp.getEid());
			emp.setJobs(jobs);
			return emp;
		});
		return emps;
	}
	
	public List<Job> queryJobEmpBySameId1(){
		String sql = "select jid, jname, eid from job";
		List<Job> jobs = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
			Job job = new Job();
			// 設定欄位
			job.setJid(rs.getInt("jid"));
			job.setJname(rs.getString("jname"));
			job.setEid(rs.getInt("eid"));
			// 通過獲得id查找job
			String sql2 = "select eid, ename, age, createtime from emp where eid=?";
			List<Emp> emps = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<Emp>(Emp.class), job.getEid());
			job.setEmp((emps.size()==0)?null:emps.get(0));
			return job;
		});
		return jobs;
	}
	
	public List<Emp> queryEmpJobBySameId2() {
		String sql = "select e.eid, e.ename, e.age, e.createtime,\r\n"
				+ "		j.jid as job_jid, j.jname as job_jname, j.eid as job_eid\r\n"
				+ "from emp as e left outer join job as j\r\n"
				+ "on j.eid = e.eid";
		ResultSetExtractor<List<Emp>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
					.addKeys("eid")
					.newResultSetExtractor(Emp.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}
	
	public List<Job> queryJobEmpBySameId2() {
		String sql = "select j.jid, j.jname, j.eid, \r\n"
				+ "	e.eid as emp_eid, e.ename as emp_ename, e.age as emp_age, e.createtime as emp_createtime\r\n"
				+ "from job as j left outer join emp as e\r\n"
				+ "on j.eid = e.eid";
		ResultSetExtractor<List<Job>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
					.addKeys("jid")
					.newResultSetExtractor(Job.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}
}

