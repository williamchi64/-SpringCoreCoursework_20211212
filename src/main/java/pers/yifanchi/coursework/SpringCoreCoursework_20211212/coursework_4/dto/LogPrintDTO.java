package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto;

import java.util.Date;
import java.util.Map;

public class LogPrintDTO {
	private String name;
	private Date createtime;
	private Integer totalCost;
	private Map<Integer, String> bookNameMap;
	private Map<Integer, Integer> bookAmountMap;
	
	public LogPrintDTO() {
		super();
	}
	public LogPrintDTO(String name, Date createtime, Integer totalCost, Map<Integer, String> bookNameMap,
			Map<Integer, Integer> bookAmountMap) {
		super();
		this.name = name;
		this.createtime = createtime;
		this.totalCost = totalCost;
		this.bookNameMap = bookNameMap;
		this.bookAmountMap = bookAmountMap;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	public Map<Integer, String> getBookNameMap() {
		return bookNameMap;
	}
	public void setBookNameMap(Map<Integer, String> bookNameMap) {
		this.bookNameMap = bookNameMap;
	}
	public Map<Integer, Integer> getBookAmountMap() {
		return bookAmountMap;
	}
	public void setBookAmountMap(Map<Integer, Integer> bookAmountMap) {
		this.bookAmountMap = bookAmountMap;
	}
	@Override
	public String toString() {
		return "LogPrintDTO [name=" + name + ", createtime=" + createtime + ", totalCost=" + totalCost
				+ ", bookNameMap=" + bookNameMap + ", bookAmountMap=" + bookAmountMap + "]";
	}
}
