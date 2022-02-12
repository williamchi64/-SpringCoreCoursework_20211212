package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;

@Aspect
@Component
public class ServiceFilter {
	private static final String pointCut = "execution(* pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.*.*(..))";
	
	@Pointcut(pointCut)
	public void pointCut() {}
	
	@AfterThrowing(value = "pointCut()", throwing = "e")
	public void throwing(JoinPoint joinPoint, Throwable e) throws DAOException {
		System.err.println(
			"Caught from service layer: "
			+ e.getMessage()
		);
	}

}
