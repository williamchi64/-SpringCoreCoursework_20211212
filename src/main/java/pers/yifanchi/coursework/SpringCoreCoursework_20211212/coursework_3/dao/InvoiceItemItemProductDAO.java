package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.Invoice;

@Repository
public class InvoiceItemItemProductDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Invoice> queryAll() {
		String sql = "select i1.id, i1.invdate, "
				+ "i2.id as item_id, i2.amount as item_amount, i2.ipid as item_ipid, i2.invid as item_invid, "
				+ "i3.id as item_product_id, i3.`text` as item_product_text, i3.price as item_product_price, i3.inventory as item_product_inventory "
				+ "from invoice as i1 "
				+ "left outer join item as i2 "
				+ "on i1.id = i2.invid "
				+ "left outer join itemproduct as i3 "
				+ "on i2.ipid = i3.id";
		ResultSetExtractor<List<Invoice>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("id")
				.newResultSetExtractor(Invoice.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}
	
	public List<Invoice> queryById(Integer id) {
		String sql = "select i1.id, i1.invdate, "
				+ "i2.id as item_id, i2.amount as item_amount, i2.ipid as item_ipid, i2.invid as item_invid, "
				+ "i3.id as item_product_id, i3.`text` as item_product_text, i3.price as item_product_price, i3.inventory as item_product_inventory "
				+ "from invoice as i1 "
				+ "left outer join item as i2 "
				+ "on i1.id = i2.invid "
				+ "left outer join itemproduct as i3 "
				+ "on i2.ipid = i3.id "
				+ "where i1.id = ?";
		ResultSetExtractor<List<Invoice>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("id")
				.newResultSetExtractor(Invoice.class);
		return jdbcTemplate.query(sql, resultSetExtractor, id);
	}
}
