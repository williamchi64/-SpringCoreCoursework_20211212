package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.ItemProduct;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.service.ItemProductService;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.service.ItemProductServiceImpl;

public class ItemProductServiceTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		ItemProductService itemProductService = ctx.getBean("itemProductServiceImpl", ItemProductServiceImpl.class);

		// All item products with related items
		List<ItemProduct> itemProducts = itemProductService.findItemProductItem();
		System.out.println("All item products with related items");
		itemProducts.forEach(System.out::println);

		// One item by id product with related items
		ItemProduct itemProduct = itemProductService.findItemProductItemById(10);
		System.out.println("One item by id product with related items");
		System.out.println(itemProduct);

		// 每一樣商品各賣了多少?
		Integer sales = itemProductService.findItemProductTotalSalesById(10);
		System.out.println("每一樣商品各賣了多少?");
		System.out.println(sales);

		// 哪一件商品賣得錢最多?
		ItemProduct highestSalesItemProduct = itemProductService.findHighestSalesItemProduct();
		System.out.println("哪一件商品賣得錢最多?");
		System.out.println(highestSalesItemProduct);
	}
}
