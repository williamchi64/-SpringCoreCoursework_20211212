package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity;

public class OrderDetail {
	private Integer id;
	private Integer oid;
	private Integer bid;
	private Integer amount;
	public OrderDetail() {
		super();
	}
	public OrderDetail(Integer bid, Integer amount) {
		super();
		this.bid = bid;
		this.amount = amount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
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
		return "OrderDetail [id=" + id + ", oid=" + oid + ", bid=" + bid + ", amount=" + amount + "]";
	}
}
