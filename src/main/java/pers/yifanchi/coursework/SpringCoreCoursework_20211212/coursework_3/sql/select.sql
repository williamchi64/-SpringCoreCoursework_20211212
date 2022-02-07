select e.ename, j.jname 
from emp as e, job as j 
where e.eid = j.eid
order by e.ename

select e.ename, count(j.jname)
from emp as e, job as j
where e.eid = j.eid
group by e.ename

select e.ename, count(j.jname) as cnt
from emp as e, job as j
where e.eid = j.eid
group by e.ename
order by cnt desc
limit 1

select e.eid, e.ename, e.age, e.createtime,
		j.jid as job_jid, j.jname as job_jname, j.eid as job_eid
from emp as e left outer join job as j
on j.eid = e.eid

select j.jid, j.jname, j.eid, 
	e.eid as emp_eid, e.ename as emp_ename, e.age as emp_age, e.createtime as emp_createtime
from job as j left outer join emp as e
on j.eid = e.eid