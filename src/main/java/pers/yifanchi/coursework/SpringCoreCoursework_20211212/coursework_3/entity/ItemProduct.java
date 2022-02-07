package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity;

import java.util.List;

public class ItemProduct extends Entity {
	// columns
	private Integer id;
	private String text;
	private Integer price;
	private Integer inventory;
	// relationship
	private List<Item> items;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "ItemProduct [id=" + id 
				+ ", text=" + text 
				+ ", price=" + price 
				+ ", inventory=" + inventory 
				+ ", items=" + this.listToString(this.items, item->item.getId())
				+ "]";
	}
}
