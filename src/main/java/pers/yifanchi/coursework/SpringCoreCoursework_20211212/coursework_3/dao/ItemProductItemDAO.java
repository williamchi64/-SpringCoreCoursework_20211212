package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.dao;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_3.entity.ItemProduct;

@Repository
public class ItemProductItemDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<ItemProduct> queryAll() {
		String sql = "select i1.id, i1.`text`, i1.price, i1.inventory, "
				+ "i2.id as item_id, i2.amount as item_amount, i2.ipid as item_ipid, i2.invid as item_invid "
				+ "from itemproduct as i1 "
				+ "left outer join item as i2 "
				+ "on i1.id = i2.ipid";
		ResultSetExtractor<List<ItemProduct>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("id")
				.newResultSetExtractor(ItemProduct.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}
	
	public List<ItemProduct> queryById(Integer id) {
		String sql = "select i1.id, i1.`text`, i1.price, i1.inventory, "
				+ "i2.id as item_id, i2.amount as item_amount, i2.ipid as item_ipid, i2.invid as item_invid "
				+ "from itemproduct as i1 "
				+ "left outer join item as i2 "
				+ "on i1.id = i2.ipid "
				+ "where i1.id = ?";
		ResultSetExtractor<List<ItemProduct>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("id")
				.newResultSetExtractor(ItemProduct.class);
		return jdbcTemplate.query(sql, resultSetExtractor, id);
	}
}
