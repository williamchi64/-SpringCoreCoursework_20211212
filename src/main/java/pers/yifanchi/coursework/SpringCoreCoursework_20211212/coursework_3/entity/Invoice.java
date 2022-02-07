package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity;

import java.util.Date;
import java.util.List;

public class Invoice extends Entity {
	// columns
	private Integer id;
	private Date invdate;
	// relationship
	private List<Item> items;
	private List<ItemProduct> itemProducts;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getInvdate() {
		return invdate;
	}
	public void setInvdate(Date invdate) {
		this.invdate = invdate;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<ItemProduct> getItemProducts() {
		return itemProducts;
	}
	public void setItemProducts(List<ItemProduct> itemProducts) {
		this.itemProducts = itemProducts;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id 
				+ ", invdate=" + invdate 
				+ ", items=" + this.listToString(this.items, item->item.getId())
				+ ", itemProducts=" + this.listToString(this.itemProducts, itemProduct->itemProduct.getId())
				+ "]";
	}
	
}
