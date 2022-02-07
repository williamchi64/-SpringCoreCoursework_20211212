package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity;

import java.util.Date;

public class Book {
	// field
	private Integer bid;
	private String bname;
	private Integer price;
	private Date createTime;
	// relationship
	private Stock stock;
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", price=" + price + ", createTime=" + createTime + ", stock="
				+ stock + "]";
	}
}
