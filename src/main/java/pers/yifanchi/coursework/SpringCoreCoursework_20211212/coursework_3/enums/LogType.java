package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.enums;

public enum LogType {
	DAO("dao", "dao"),
	SERVICE("service", "service");;
	
	private String type;
	private String packageName;
	
	private LogType(String type, String packageName) {
		this.type = type;
		this.packageName = packageName;
	}
	
	public String toString() {
		return this.type;
	}
	
	public String getPackage() {
		return this.packageName;
	}
}
