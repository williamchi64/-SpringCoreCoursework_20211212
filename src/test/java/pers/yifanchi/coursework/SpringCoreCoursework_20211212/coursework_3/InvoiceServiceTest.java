package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Invoice;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.ItemProduct;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.service.InvoiceService;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.service.InvoiceServiceImpl;

public class InvoiceServiceTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("./conf/jdbc-config.xml");
		InvoiceService invoiceService = ctx.getBean("invoiceServiceImpl", InvoiceServiceImpl.class);

		// All invoices with related items and item products
		List<Invoice> invoices = invoiceService.findInvoiceItemItemProduct();
		System.out.println("all invoices with related items and item products");
		invoices.forEach(System.out::println);

		// One invoice by id with related items and item products
		Invoice invoice = invoiceService.findInvoiceItemItemProductById(14);
		System.out.println("one invoice by id with related items and item products");
		System.out.println(invoice);

		// 每一張發票有那些商品?
		List<ItemProduct> itemProducts = invoiceService.findItemProductByInvoiceId(14);
		System.out.println("每一張發票有那些商品?");
		itemProducts.forEach(i->System.out.println(i));

		// 每一張發票有幾件商品?
		Integer amount = invoiceService.findItemAmountByInvoiceId(14);
		System.out.println("每一張發票有幾件商品?");
		System.out.println(amount);

		// 每一張發票價值多少?
		Integer price = invoiceService.findItemProductPriceByInvoiceId(14);
		System.out.println("每一張發票價值多少?");
		System.out.println(price);

		// 哪一張發票價值最高?
		Invoice highestSpendinvoice = invoiceService.findHighestSpendInvoice();
		System.out.println("哪一張發票價值最高?");
		System.out.println(highestSpendinvoice);
	}
}
