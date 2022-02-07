-- order log
select 
	ol.id, ol.wid, ol.money, ol.create_time,
	od.id as order_detail_id, od.oid as order_detail_oid, 
	od.bid as order_detail_bid, od.amount as order_detail_amount
from orderlog ol
left join orderdetail od
	on ol.id = od.oid
-- by id
	where ol.id = ?
-- by wid
	where ol.wid = ?