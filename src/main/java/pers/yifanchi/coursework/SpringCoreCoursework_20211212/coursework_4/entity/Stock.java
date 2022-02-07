package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity;

public class Stock {
	private Integer sid;
	private Integer bid;
	private Integer amount;
	
	public Stock() {
		super();
	}
	public Stock(Integer bid, Integer amount) {
		super();
		this.bid = bid;
		this.amount = amount;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Stock [sid=" + sid + ", bid=" + bid + ", amount=" + amount + "]";
	}
}
