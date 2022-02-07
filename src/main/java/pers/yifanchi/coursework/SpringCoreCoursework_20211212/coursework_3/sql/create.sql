-- 建立 emp 資料表
create table if not exists emp (
	eid int not null auto_increment, -- 主鍵（自動產生序號，過號不返回）
	ename varchar(50) not null unique, -- 員工姓名
	age int, -- 員工年齡
	createtime timestamp default current_timestamp, -- 檔案建立時間
	primary key(eid)	
)

-- 建立 log 資料表
create table if not exists log (
	lid int not null auto_increment, -- 主鍵（自動產生序號，過號不返回）
	log_type varchar(30) not null, -- 
	log_state varchar(30) not null, -- 
	method_name varchar(50) not null, --
	log_start_timestamp timestamp default current_timestamp, -- 
	log_end_timestamp timestamp default current_timestamp, -- 
	description varchar(2000),
	primary key(lid)	
)

-- 建立 job 資料表 -- 一個員工對應多個job，一對多
create table if not exists job (
	jid int not null auto_increment, -- 主鍵
	jname varchar(50) not null unique, -- 工作名稱
	eid int, -- 員工ID
	primary key(jid),
	foreign key(eid) references emp(eid) -- 外鍵約束
)


-- 新增 itemProduct
CREATE TABLE IF NOT exists ItemProduct ( -- 商品項目
	id INTEGER NOT NULL auto_increment,
	text VARCHAR(50) not null,
	price INTEGER NOT NULL,
	inventory INTEGER NOT NULL,
	PRIMARY KEY (`id`)
);

-- 新增 invoice
CREATE TABLE Invoice ( -- 發票
	id INTEGER NOT NULL auto_increment, -- 發票序號(主鍵)
	invdate timestamp default current_timestamp not null, -- 發票日期
	PRIMARY KEY (id)
);

-- 新增 item
CREATE TABLE Item ( -- 發票項目
	id INTEGER NOT NULL auto_increment, -- 項目序號(主鍵)
	amount INTEGER NOT NULL, -- 數量
	ipid INTEGER NOT NULL, -- 商品項目序號
	invid INTEGER NOT NULL, -- 發票序號
	PRIMARY KEY (id),
	FOREIGN KEY (ipid) REFERENCES ItemProduct(id),
	FOREIGN KEY (invid) REFERENCES Invoice(id)
);