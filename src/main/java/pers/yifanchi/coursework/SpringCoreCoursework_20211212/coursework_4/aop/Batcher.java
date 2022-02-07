package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class Batcher {

	private static final int SINGLE_BATCH = 1;
	private static final int SMALL_BATCH = 4;
	private static final int MEDIUM_BATCH = 9;
	private static final int LARGE_BATCH = 19;
	
	private static final String pointCut = "execution(* pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.*.*(..))";

	@Pointcut(pointCut)
	public void pointCut() {}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) {
		List<Object> result = new ArrayList<>();
		Object[] params = new Object[0];
		try {
			Object[] args = joinPoint.getArgs();
			int arrayIndex = args.length-1;
			/* 
			 * if variable-length arguments are not detected, 
			 * the batch processing of the join-point arguments will be not executed
			 */
			if (args[arrayIndex].getClass().isArray())
				params = (Object[]) args[arrayIndex];
			if (params.length <= 1)
				return (List<?>) joinPoint.proceed();

			do {
				int batchCut = generateBatch(params.length);
				args[arrayIndex] = generateParam(batchCut, params);
				Object objResult = joinPoint.proceed(args);
				result.addAll((List<?>) objResult);
				params = generateUnusedParam(batchCut, params);
			} while (params.length != 0);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// utils
	private int generateBatch(int batch) {
		if (batch / LARGE_BATCH > 0) {
			return LARGE_BATCH;
		} else if (batch / MEDIUM_BATCH > 0) {
			return MEDIUM_BATCH;
		} else if (batch / SMALL_BATCH > 0) {
			return SMALL_BATCH;
		} else {
			return SINGLE_BATCH;
		}
	}
	private Object[] generateParam(int batch, Object...params) {
		return Arrays.copyOfRange(params, 0, batch);
	}
	private Object[] generateUnusedParam(int batch, Object...params) {
		return Arrays.copyOfRange(params, batch, params.length);
	}
	
}
