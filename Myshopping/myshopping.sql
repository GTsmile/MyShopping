-- 用户表 
create database myshopping;  

use myshopping;
create table users (
  users_id int(10) PRIMARY key AUTO_INCREMENT,       /*--用户编号 */
  username varchar(32) not null,                     /*--用户姓名*/ 
  passwd varchar(32) not null,                       /*--用户密码*/
  email varchar(64) not null,                        /*--用户Email*/
  tel varchar(20) not null,                          /*--电话号码*/
  grade int(2) not null                                /*--用户等级 */
);

#测试数据
use myshopping;
insert into users(username,passwd,email,tel,grade) values('shunping','shunping','shunping@sohu.com','6191738','1');
insert into users(username,passwd,email,tel,grade) values('test1','test1','test1@sohu.com','6191739','2');
insert into users(username,passwd,email,tel,grade) values('test2','test2','test2@sohu.com','6191740','3');
insert into users(username,passwd,email,tel,grade) values('test3','test3','test3@sohu.com','6191741','4');
insert into users(username,passwd,email,tel,grade) values('test4','test4','test4@sohu.com','6191742','5');


#商品表
create table goods(                                                        
 book_id varchar(20) PRIMARY KEY,             #书的编号
 bookname varchar(50) not null,               #书名
 author varchar(50) not null,                 #作者
 publishHouse varchar(100) not null,          #出版社 
 bookprice float not null,                    #书的价格 
 reserve int(10) default 1000 not null        #库存剩余量
);

#测试数据
insert into goods(book_id,bookname,author,publishHouse,bookprice,reserve)
           values('9787121155796','疯狂Java讲义','李刚','电子工业出版社','79.00','100');
insert into goods(book_id,bookname,author,publishHouse,bookprice,reserve)
           values('9787121135767 ','疯狂Android讲义','李刚','电子工业出版社','89.00','90');
insert into goods(book_id,bookname,author,publishHouse,bookprice,reserve)
           values('9787115428028','python编程从入门到实践','[美]埃里克·马瑟斯','人民邮电出版社','89.00','180');
insert into goods(book_id,bookname,author,publishHouse,bookprice,reserve)
           values('9787115178503','Python核心编程（第二版）','[美]Wesley J. Chun（陳仲才）','人民邮电出版社','89.00','120');
insert into goods(book_id,bookname,author,publishHouse,bookprice,reserve)
           values('9787115178505','Head First HTML与CSS（第2版）','Elisabeth Robson/ Eric Freeman','中国电力出版社','89.00','120');      
insert into goods(book_id,bookname,author,publishHouse,bookprice,reserve)
           values('9787512344778','孙子兵法','孙子','北京燕山出版社','98.00','80');
insert into goods(book_id,bookname,author,publishHouse,bookprice,reserve)
           values('9787512344779','三十六计','古人糖','华中科技出版社','97.00','90');
insert into goods(book_id,bookname,author,publishHouse,bookprice,reserve)
           values('9787512344780','红楼梦','曹雪芹','中国人民出版社','96.00','100');
insert into goods(book_id,bookname,author,publishHouse,bookprice,reserve)
           values('9787512344781','三国演义','罗贯中','人民邮电出版社','95.00','110');

#订单表
create table orderform(
  ord_id int PRIMARY key AUTO_INCREMENT not null,       #自增长订单库编号
  users_id int(10) not null,                            #订单提交用户编号
  ord_totalPrice float default 0 not null,                   #订单总价
  ord_time timestamp NULL default CURRENT_TIMESTAMP,     #订单提交时间
  #orderDate date default SYSDATE,
  constraint user_id_fk foreign key(users_id)  references users(users_id)  
);

#insert into orderform(users_id,ord_totalPrice) values(0,153.0)

#订单细节表(该订单究竟买了什么商品)
create table orderdetail(
  serial_number int PRIMARY key AUTO_INCREMENT,                #自增长订单库编号
  ord_id int not null,                                  #订单编号
  goods_id varchar(20),                                 #商品编号  外键
  goods_num int default 0 not null,                     #商品数量
  #ord_price float default 0 not null,                   #商品总价小计  
  constraint ord_id_fk foreign key(ord_id)  references orderform(ord_id),   #外键
  constraint book_id_fk foreign key(goods_id)  references goods(book_id)   #外键        
)charset utf8;

insert into orderdetail(ord_id,goods_id,goods_num) values()






