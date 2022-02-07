package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Item;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.ItemProduct;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.ItemProductItemDAO;

@Service
public class ItemProductServiceImpl implements ItemProductService {
	@Autowired
	private ItemProductItemDAO itemProductItemDAO;
	@Override
	public List<ItemProduct> findItemProductItem() {
		return itemProductItemDAO.queryAll();
	}
	@Override
	public ItemProduct findItemProductItemById(Integer id) {
		List<ItemProduct> itemProducts = itemProductItemDAO.queryById(id);
		return itemProducts.size() == 0? null: itemProducts.get(0);
	}

	private List<Item> getItem(ItemProduct itemProduct) {
		return (itemProduct == null || itemProduct.getItems().get(0).getId() == null)? 
				null: itemProduct.getItems();
	}

	private Integer getTotalSales(ItemProduct itemProduct) {
		List<Item> items = this.getItem(itemProduct);
		return (itemProduct == null || items == null)? 0: items.stream()
				.collect(
						Collectors.summingInt(
								item->item.getAmount() * itemProduct.getPrice()
						)
				);
	}
	/**
	 * 
	 * 每一樣商品各賣了多少?
	 * 
	 */
	@Override
	public Integer findItemProductTotalSalesById(Integer id) {
		ItemProduct itemProduct = this.findItemProductItemById(id);
		return this.getTotalSales(itemProduct);
	}
	
	private List<ItemProduct> getSalesSorted(List<ItemProduct> itemProducts) {
		return itemProducts.stream()
				.sorted(
						Comparator.comparingInt(
								itemProduct->this.getTotalSales((ItemProduct) itemProduct)
						).reversed()
				).collect(Collectors.toList());
	}
	/**
	 * 
	 * 哪一件商品賣得錢最多?
	 * 
	 */
	@Override
	public ItemProduct findHighestSalesItemProduct() {
		List<ItemProduct> itemProducts = this.findItemProductItem();
		return itemProducts.size() == 0? null: this.getSalesSorted(itemProducts).get(0);
	}
}
