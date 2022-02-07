-- HM: 建立交易記錄 order_log
-- wname 在 time 買了 bname amount 本 總計money
-- 資料表如何創建?

-- 交易 TX(transaction) 資料表
-- book, stock, wallet
-- book
create table if not exists book(
	bid integer not null auto_increment,
	bname varchar(20) not null,
	price integer default 0,
	create_time timestamp default current_timestamp,
	primary key (bid)
);
-- stock
create table if not exists stock(
	sid integer not null auto_increment,
	bid integer not null, -- book bid
	amount integer default 0,
	primary key (sid),
	foreign key (bid) references book (bid)
);
-- wallet
create table if not exists wallet(
	wid integer not null auto_increment,
	wname varchar(20) not null,
	money integer default 0,
	primary key (wid)
);
-- order log
create table if not exists orderlog(
	id integer not null auto_increment,
	wid integer not null, -- wallet wid, who buy
	money integer not null, -- total price
	create_time timestamp default current_timestamp,
	primary key (id),
	foreign key (wid) references wallet (wid)
);
-- order detail
create table if not exists orderdetail(
	id integer not null auto_increment,
	oid integer not null, -- order log oid, to group by order
	bid integer not null, -- book bid, what bought
	amount integer not null, -- book amount, how many bought
	primary key (id),
	foreign key (oid) references orderlog (id),
	foreign key (bid) references book (bid)
);