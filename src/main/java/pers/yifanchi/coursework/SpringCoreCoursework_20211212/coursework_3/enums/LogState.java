package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.enums;

public enum LogState {
	START("start"),
	SUCCESS("success"),
	FAILURE("failure");
	
	private String string;
	
	private LogState(String string) {
		this.string = string;
	}
	
	public String toString() {
		return this.string;
	}
}
