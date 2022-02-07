package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.annotation.FilterParam;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.DAOException;

@Aspect
@Component
public class DAOFIlter {

	private static final String pointCut = "execution(* pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.*.*(..))";

	@Pointcut(pointCut)
	public void pointCut() {}

	@Before(value = "pointCut()")
	public void before(JoinPoint joinPoint) throws DAOException {
		Object[] args = joinPoint.getArgs();
		for (Object arg: args) {
			if (arg==null)
				throw new DAOException("DAO parameters contain null");
			if (arg.getClass().isArray())
				if (Arrays.stream((Object[]) arg).anyMatch(e->e==null))
					throw new DAOException("DAO array parameters contain null");
			if (arg instanceof Collection)
				if (((Collection<?>) arg).stream().anyMatch(e->e==null))
					throw new DAOException("DAO collection parameters contain null");
		}
	}

	@AfterReturning(value="pointCut()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) throws DAOException {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		FilterParam filterParam = method.getAnnotation(FilterParam.class);
		String msg = "Return nothing from dao request";
		if (filterParam!=null) {
			msg = filterParam.value();
			if (filterParam.isFormat()) {
				Object[] objs = (Object[]) joinPoint.getArgs();
				String[] formatParam = new String[objs.length];
				for (int i=0; i<objs.length; i++) {
					formatParam[i] = objs[i].getClass().isArray()?
						Arrays.asList((Object[]) objs[i]).stream()
							.map(Object::toString)
							.collect(Collectors.joining(", ")):
						objs[i].toString();
				}
				msg = String.format(msg, (Object[]) formatParam);
			}
		}
		if (result == null)
			throw new DAOException(msg);
	}
	@AfterThrowing("pointCut()")
	public void throwing(JoinPoint joinPoint) throws DAOException {}
}
