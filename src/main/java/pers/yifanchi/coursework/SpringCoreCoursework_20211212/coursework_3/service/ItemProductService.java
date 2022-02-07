package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.service;

import java.util.List;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.ItemProduct;

public interface ItemProductService {
	List<ItemProduct> findItemProductItem();
	ItemProduct findItemProductItemById(Integer id);
	// 每一樣商品各賣了多少?
	Integer findItemProductTotalSalesById(Integer id);
	// 哪一件商品賣得錢最多?
	ItemProduct findHighestSalesItemProduct();
}
