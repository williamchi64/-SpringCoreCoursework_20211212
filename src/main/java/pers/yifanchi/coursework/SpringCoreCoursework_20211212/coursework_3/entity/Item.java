package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity;

public class Item {
	private Integer id;
	private Integer amount;
	private Integer ipid;
	private Integer invid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getIpid() {
		return ipid;
	}
	public void setIpid(Integer ipid) {
		this.ipid = ipid;
	}
	public Integer getInvid() {
		return invid;
	}
	public void setInvid(Integer invid) {
		this.invid = invid;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", amount=" + amount + ", ipid=" + ipid + ", invid=" + invid + "]";
	}
	
}
