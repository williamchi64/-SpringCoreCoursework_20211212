package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.service;

import java.util.List;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Invoice;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.ItemProduct;

public interface InvoiceService {
	List<Invoice> findInvoiceItemItemProduct();
	Invoice findInvoiceItemItemProductById(Integer id);
	// 每一張發票有那些商品?
	List<ItemProduct> findItemProductByInvoiceId(Integer id);
	// 每一張發票有幾件商品?
	Integer findItemAmountByInvoiceId(Integer id);
	// 每一張發票價值多少?
	Integer findItemProductPriceByInvoiceId(Integer id);
	// 哪一張發票價值最高?
	Invoice findHighestSpendInvoice();
}
