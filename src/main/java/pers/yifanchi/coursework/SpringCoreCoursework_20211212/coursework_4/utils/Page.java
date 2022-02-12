package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.utils;

public class Page {
	private Integer start;
	private Integer total;
	
	public Page() {
		super();
	}

	public Page(Integer start, Integer total) {
		super();
		this.start = start;
		this.total = total;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
}
