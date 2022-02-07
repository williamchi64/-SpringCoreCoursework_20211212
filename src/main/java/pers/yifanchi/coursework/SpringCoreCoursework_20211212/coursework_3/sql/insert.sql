-- 新增 emp 範例資料
insert into emp(ename, age) values('john', 28);
insert into emp(ename, age) values('mary', 28);
insert into emp(ename, age) values('bobo', 28);

-- 新增 job 範例資料
insert into job(jname, eid) values('admin1', 2);
insert into job(jname, eid) values('accounting1', 2);
insert into job(jname, eid) values('saler1', 3);
insert into job(jname, eid) values('secretary1', 5);
insert into job(jname) values('admin2');
insert into job(jname) values('accounting2');
insert into job(jname) values('saler2');
insert into job(jname) values('secretary2');

-- itemproduct 範例
insert into itemproduct(text, price, inventory) values ('jacket1', 100, 10);
insert into itemproduct(text, price, inventory) values ('jacket2', 150, 5);
insert into itemproduct(text, price, inventory) values ('shirt1', 50, 20);
insert into itemproduct(text, price, inventory) values ('shirt2', 60, 25);
insert into itemproduct(text, price, inventory) values ('shirt3', 90, 15);
insert into itemproduct(text, price, inventory) values ('t-shirt1', 30, 30);
insert into itemproduct(text, price, inventory) values ('t-shirt12', 45, 20);
insert into itemproduct(text, price, inventory) values ('jeans1', 60, 20);
insert into itemproduct(text, price, inventory) values ('suit-pants1', 200, 5);
insert into itemproduct(text, price, inventory) values ('suit-pants2', 300, 5);
insert into itemproduct(text, price, inventory) values ('suit-clothes1', 220, 5);
insert into itemproduct(text, price, inventory) values ('socks1', 10, 40);
insert into itemproduct(text, price, inventory) values ('socks2', 5, 53);
insert into itemproduct(text, price, inventory) values ('coat-gloves1', 10, 46);
insert into itemproduct(text, price, inventory) values ('briefs1', 5, 52);
insert into itemproduct(text, price, inventory) values ('briefs2', 7, 52);
insert into itemproduct(text, price, inventory) values ('bra1', 20, 20);
insert into itemproduct(text, price, inventory) values ('bra2', 15, 35);
insert into itemproduct(text, price, inventory) values ('panties1', 10, 47);
insert into itemproduct(text, price, inventory) values ('panties2', 20, 30);

-- invoice 範例
insert into invoice(invdate) values (2022-01-01 10:49:15);

-- item 範例
insert into item(amount, ipid, invid) values (5, 5, 1);
insert into item(amount, ipid, invid) values (1, 2, 2);
insert into item(amount, ipid, invid) values (4, 15, 3);
insert into item(amount, ipid, invid) values (2, 19, 3);
insert into item(amount, ipid, invid) values (4, 20, 3);

insert into item(amount, ipid, invid) values (5, 15, 4);
insert into item(amount, ipid, invid) values (3, 19, 4);
insert into item(amount, ipid, invid) values (2, 8, 5);
insert into item(amount, ipid, invid) values (1, 11, 5);
insert into item(amount, ipid, invid) values (2, 17, 5);

insert into item(amount, ipid, invid) values (3, 3, 6);
insert into item(amount, ipid, invid) values (3, 9, 7);
insert into item(amount, ipid, invid) values (2, 9, 8);
insert into item(amount, ipid, invid) values (2, 8, 9);
insert into item(amount, ipid, invid) values (3, 6, 10);

insert into item(amount, ipid, invid) values (1, 12, 10);
insert into item(amount, ipid, invid) values (1, 15, 11);
insert into item(amount, ipid, invid) values (1, 9, 12);
insert into item(amount, ipid, invid) values (5, 12, 12);
insert into item(amount, ipid, invid) values (3, 18, 12);

insert into item(amount, ipid, invid) values (3, 6, 13);
insert into item(amount, ipid, invid) values (1, 19, 13);
insert into item(amount, ipid, invid) values (2, 1, 14);
insert into item(amount, ipid, invid) values (5, 10, 14);
insert into item(amount, ipid, invid) values (3, 6, 15);

insert into item(amount, ipid, invid) values (1, 13, 15);
insert into item(amount, ipid, invid) values (1, 11, 16);
insert into item(amount, ipid, invid) values (2, 18, 16);
insert into item(amount, ipid, invid) values (4, 20, 16);
insert into item(amount, ipid, invid) values (2, 6, 17);

insert into item(amount, ipid, invid) values (2, 2, 18);
insert into item(amount, ipid, invid) values (1, 11, 18);
insert into item(amount, ipid, invid) values (1, 18, 18);
insert into item(amount, ipid, invid) values (1, 19, 19);
insert into item(amount, ipid, invid) values (1, 17, 20);









