package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerFilter {
	private static final String pointCut = "execution(* pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller.*.*(..))";
	
	@Pointcut(pointCut)
	public void pointCut() {}
	
	@AfterThrowing(value = "pointCut()", throwing = "e")
	public void throwing(JoinPoint joinPoint, Throwable e) {
		System.err.println(
			"Caught from controller layer: "
			+ e.getMessage()
		);
	}

}
