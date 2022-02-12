package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto;

import java.util.Date;

public class BookPrintDTO {
	private Integer bid;
	private String bname;
	private Integer price;
	private Date createTime;
	private Integer amount;
	
	public BookPrintDTO() {
		super();
	}

	public BookPrintDTO(Integer bid, String bname, Integer price, Date createTime, Integer amount) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.price = price;
		this.createTime = createTime;
		this.amount = amount;
	}

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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BookPrintDTO [bid=" + bid + ", bname=" + bname + ", price=" + price + ", createTime=" + createTime
				+ ", amount=" + amount + "]";
	}
}
