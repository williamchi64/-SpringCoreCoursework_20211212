package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity;

import java.util.Date;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.enums.LogState;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.enums.LogType;

public class Log {
	private Integer lid;
	private LogType logType;
	private LogState logState;
	private String methodName;
	private Date logStartTimestamp;
	private Date logEndTimestamp;
	private String description;
	public Log() {
		super();
	}
	public Log(LogType logType, LogState logState, String methodName, Date logStartTimestamp, Date logEndTimestamp,
			String description) {
		super();
		this.logType = logType;
		this.logState = logState;
		this.methodName = methodName;
		this.logStartTimestamp = logStartTimestamp;
		this.logEndTimestamp = logEndTimestamp;
		this.description = description;
	}
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public LogType getLogType() {
		return logType;
	}
	public void setLogType(LogType logType) {
		this.logType = logType;
	}
	public LogState getLogState() {
		return logState;
	}
	public void setLogState(LogState logState) {
		this.logState = logState;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Date getLogStartTimestamp() {
		return logStartTimestamp;
	}
	public void setLogStartTimestamp(Date logStartTimestamp) {
		this.logStartTimestamp = logStartTimestamp;
	}
	public Date getLogEndTimestamp() {
		return logEndTimestamp;
	}
	public void setLogEndTimestamp(Date logEndTimestamp) {
		this.logEndTimestamp = logEndTimestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Log [lid=" + lid + ",\n logType=" + logType + ",\n logState=" + logState + ",\n methodName=" + methodName
				+ ",\n logStartTimestamp=" + logStartTimestamp + ",\n logEndTimestamp=" + logEndTimestamp + ",\n description="
				+ description + "]\n";
	}
	
}
