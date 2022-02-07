package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.aop;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Log;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.enums.LogState;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.enums.LogType;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.LogDAO;

@Aspect
@Component
public class Logger {
	
	private String colnStr;
	private String lineStr;
	private static final String pointCut = "execution(* pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.*.*.*(..)) && " +
			"!execution(* pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.LogDAO.*(..)) && " +
			"!execution(* pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.aop.Logger.*(..))";

	@Autowired
	private LogDAO logDAO;

	@Pointcut(pointCut)
	public void pointCut() {}
	/**
	 * 
	 * @param joinPoint: to generate a log instance
	 * @return result of the join point process
	 */
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object result = null;
		Log log = null;
		Date logStartTimestamp = this.showLog(joinPoint, System.out, LogState.START);
		try {
			// join point main proceed
			result = joinPoint.proceed();
			Date logEndTimestamp = this.showLog(joinPoint, System.out, LogState.SUCCESS);
			log = this.constructLog(joinPoint, LogState.SUCCESS, logStartTimestamp, logEndTimestamp);
		} catch (Throwable e) {
			Date logEndTimestamp = this.showLog(joinPoint, System.err, LogState.FAILURE, e.getMessage());
			log = this.constructLog(joinPoint, LogState.FAILURE, logStartTimestamp, logEndTimestamp, e.getMessage());
		} finally {
			Long id = this.logDAO.insertLog(log);
			System.out.printf("Log id: %d is inserted\n", id);
		}
		return result;
	}
	/**
	 * 
	 * @param joinPoint: to determine Class and method name
	 * @param printStream: to decide terminal print type (out/ err)
	 * @param state: showed in terminal
	 * @return timestamp of a log
	 */
	private Date showLog(ProceedingJoinPoint joinPoint, PrintStream printStream, LogState state) {
		return this.showLog(joinPoint, printStream, state, "");
	}
	private Date showLog(ProceedingJoinPoint joinPoint, PrintStream printStream, LogState state, String errMsg) {
		Date nowDatetime = new Date();
		String stateString = state.toString();
		String methodString = joinPoint.getSignature().toShortString();
		String nowString = this.datetimeString(nowDatetime);
		this.updateColnLine(stateString.length(), methodString.length(), nowString.length());
		System.out.print(lineStr + colnStr + lineStr);
		printStream.printf(
				this.generateFormatString(stateString.length(), methodString.length(), nowString.length()) + " %s\n",
				stateString, 
				methodString,
				nowString, 
				errMsg
		);
		System.out.print(lineStr);
		return nowDatetime;
	}
	private String generateFormatString(int num1, int num2, int num3) {
		num1 = num1 <= 5? 5: num1;
		num2 = num2 <= 11? 11: num2;
		num3 = num3 <= 13? 13: num3;
		return "| %-" + num1 + "s | %-" + num2 + "s | %-" + num3 + "s |";
	}
	private String generateTitleString(int num1, int num2, int num3) {
		String format = this.generateFormatString(num1, num2, num3);
		return String.format(format, "state", "method_name", "log_timestamp") + "\n";
	}
	private String generateLineString(int num1, int num2, int num3) {
		String s1 = String.join("", Collections.nCopies(num1+2, "-"));
		String s2 = String.join("", Collections.nCopies(num2+2, "-"));
		String s3 = String.join("", Collections.nCopies(num3+2, "-"));
		return "+" + s1 + "+" + s2 + "+" + s3 + "+\n";
	}
	private void updateColnLine(int num1, int num2, int num3) {
		colnStr = this.generateTitleString(num1, num2, num3);
		lineStr = this.generateLineString(num1, num2, num3);
	}
	/**
	 * 
	 * @param date: transfer date to terminal print form
	 * @return String in simple date format 
	 */
	private String datetimeString(Date date) {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
	}
	/**
	 * 
	 * @param joinPoint: to determine a package name to determine the data process type (DAO, SERVICE) , Class and method name
	 * @param state: state of a log (start/ success/ failure)
	 * @param logStartTimestamp: timestamp of start of the process
	 * @param logEndTimestamp: timestamp of end of the process
	 * @return Log constructed instance
	 */
	private Log constructLog(ProceedingJoinPoint joinPoint, LogState state, Date logStartTimestamp, Date logEndTimestamp) {
		return this.constructLog(joinPoint, state, logStartTimestamp, logEndTimestamp, null);
	}
	private Log constructLog(ProceedingJoinPoint joinPoint, LogState state, Date logStartTimestamp, Date logEndTimestamp, String errMsg) {
		String[] packageArry = joinPoint.getSignature().getDeclaringType().getPackageName().split("\\.");
		String packageString = packageArry[packageArry.length-1];
		String methodName = joinPoint.getSignature().toShortString(); 
		return new Log(this.packageToType(packageString), state, methodName, logStartTimestamp, logEndTimestamp, errMsg);
	}
	/**
	 * 
	 * @param packageString: package name to determine the data process type (DAO, SERVICE)
	 * @return a data process type (DAO, SERVICE)
	 */
	private LogType packageToType(String packageString) {
		LogType result = null;
		for (LogType logType: LogType.class.getEnumConstants()) {
			result = packageString.equals(logType.getPackage())? logType: null;
			if (result != null)
					break;
		}
		return result;
	}
}
