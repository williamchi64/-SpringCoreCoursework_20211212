package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity;

import java.util.Date;
import java.util.List;

public class OrderLog {
	private Integer id;
	private Integer wid;
	private Integer money;
	private Date createTime;
	private List<OrderDetail> orderDetails;
	public OrderLog() {
		super();
	}
	public OrderLog(Integer wid, Integer money) {
		super();
		this.wid = wid;
		this.money = money;
	}
	public OrderLog(Integer wid, Integer money, List<OrderDetail> orderDetails) {
		super();
		this.wid = wid;
		this.money = money;
		this.orderDetails = orderDetails;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	@Override
	public String toString() {
		return "OrderLog [id=" + id 
				+ ", wid=" + wid 
				+ ", money=" + money 
				+ ", createTime=" + createTime 
				+ ", orderDetails=" + 
				(orderDetails==null||orderDetails.size()==0?null:orderDetails)
				+ "]";
	}
}
