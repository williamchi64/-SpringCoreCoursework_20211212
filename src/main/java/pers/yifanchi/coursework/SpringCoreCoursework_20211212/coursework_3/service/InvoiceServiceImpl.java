package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Invoice;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Item;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.ItemProduct;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao.InvoiceItemItemProductDAO;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	private InvoiceItemItemProductDAO invoiceItemItemProductDAO;
	@Override
	public List<Invoice> findInvoiceItemItemProduct() {
		return invoiceItemItemProductDAO.queryAll();
	}
	@Override
	public Invoice findInvoiceItemItemProductById(Integer id) {
		List<Invoice> invoices = invoiceItemItemProductDAO.queryById(id);
		return invoices.size() == 0? null: invoices.get(0);
	}

	private List<Item> getItems(Invoice invoice) {
		return invoice == null? null: invoice.getItems();
	}

	private List<ItemProduct> getItemProducts(Invoice invoice) {
		return invoice == null? null: invoice.getItemProducts();
	}
	/**
	 *
	 * 每一張發票有那些商品?
	 *
	 */
	@Override
	public List<ItemProduct> findItemProductByInvoiceId(Integer id) {
		Invoice invoice = this.findInvoiceItemItemProductById(id);
		return this.getItemProducts(invoice);
	}
	
	private Integer getTotalAmount(Invoice invoice) {
		List<Item> items = this.getItems(invoice);
		return (invoice == null || items == null)? 0: items.stream()
				.collect(Collectors.summingInt(Item::getAmount));
	}
	/**
	 * 
	 * 每一張發票有幾件商品?
	 * 
	 */
	@Override
	public Integer findItemAmountByInvoiceId(Integer id) {
		Invoice invoice = this.findInvoiceItemItemProductById(id);
		return this.getTotalAmount(invoice);
	}

	private Integer getTotalSpend(Invoice invoice) {
		List<Item> items = this.getItems(invoice);
		List<ItemProduct> itemProducts = this.getItemProducts(invoice);
		if (invoice == null || items == null || itemProducts == null)
			return 0;
		Map<Integer,Integer> amountMap = items.stream()
				.collect(Collectors.toMap(Item::getIpid, Item::getAmount));
		return itemProducts.stream()
				.collect(
						Collectors.summingInt(
								itemProduct->amountMap.get(itemProduct.getId()) * itemProduct.getPrice()
						)
				);
	}
	/**
	 * 
	 * 每一張發票價值多少?
	 * 
	 */
	@Override
	public Integer findItemProductPriceByInvoiceId(Integer id) {
		Invoice invoice = this.findInvoiceItemItemProductById(id);
		return this.getTotalSpend(invoice);
	}
	
	private List<Invoice> getPriceSorted(List<Invoice> invoices) {
		return invoices.stream()
				.sorted(
						Comparator.comparingInt(
								invoice->this.getTotalSpend((Invoice) invoice)
						).reversed()
				).collect(Collectors.toList());
	}
	/**
	 * 
	 * 哪一張發票價值最高?
	 * 
	 */
	@Override
	public Invoice findHighestSpendInvoice() {
		List<Invoice> invoices = this.findInvoiceItemItemProduct();
		return invoices.size() == 0? null: this.getPriceSorted(invoices).get(0);
	}
}
