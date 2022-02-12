package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity;

public class Wallet {
	private Integer wid;
	private String wname;
	private Integer money;
	public Wallet() {
		super();
	}
	public Wallet(String wname, Integer money) {
		super();
		this.wname = wname;
		this.money = money;
	}
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "Wallet [wid=" + wid + ", wname=" + wname + ", money=" + money + "]";
	}
}
