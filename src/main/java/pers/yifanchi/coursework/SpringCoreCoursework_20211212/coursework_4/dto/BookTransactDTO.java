package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dto;

import java.util.Map;

public class BookTransactDTO {
	// wrap in controller
	private Integer wid;
	private Map<Integer, Integer> bookAmountMap;
	// set in service
	private Map<Integer, Integer[]> bookPriceStockMap;
	private Integer totalPrice;
	
	public BookTransactDTO() {
		super();
	}

	public BookTransactDTO(Integer wid, Map<Integer, Integer> bookAmountMap) {
		super();
		this.wid = wid;
		this.bookAmountMap = bookAmountMap;
	}

	public Integer getWid() {
		return wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public Map<Integer, Integer> getBookAmountMap() {
		return bookAmountMap;
	}

	public void setBookAmountMap(Map<Integer, Integer> bookAmountMap) {
		this.bookAmountMap = bookAmountMap;
	}

	public Map<Integer, Integer[]> getBookPriceStockMap() {
		return bookPriceStockMap;
	}

	public void setBookPriceStockMap(Map<Integer, Integer[]> bookPriceStockMap) {
		this.bookPriceStockMap = bookPriceStockMap;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "BookTransactDTO [wid=" + wid + ", bookAmountMap=" + bookAmountMap + ", bookPriceStockMap="
				+ bookPriceStockMap + ", totalPrice=" + totalPrice + "]";
	}

}
