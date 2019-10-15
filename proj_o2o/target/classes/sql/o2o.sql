create database o2o;
use o2o;
create table area(
 area_id  int(10)primary key auto_increment not null,
 area_name varchar(20) unique key not null,
 priority int(2) not null default 0,
 create_time datetime default null,
 last_edit_time datetime default null
 )charset utf8;
 
 create table person_info(
 user_id int(10) primary key auto_increment not null,
 name varchar(50) default null,
 profile_image varchar(50) default null,
 email varchar(50) default null,
 gender varchar(2) default null,
 enable_status int(5) not null default 0 comment '0:禁止使用本商城，1:允许使用 本商城',
 user_type int(5) not null default 1 comment '1:顾客 2:店家 3：超级管理员',
 create_time datetime default null,
 last_edit_time datetime default null
 )charset utf8;
 
 create table wechat_auth(
 wechat_auth_id int(10) primary key not null auto_increment,
 open_id varchar(100) not null,
 create_time datetime default null,
 user_id int(10),
 foreign key(user_id) references person_info(user_id)
 )charset utf8;
 
 create table local_auth(
 local_auth_id int(10) primary key not null auto_increment,
 user_id int(10),
 username varchar(50) unique key not null,
 password varchar(50) not null,
 create_time datetime default null,
 foreign key(user_id) references person_info(user_id)
 )charset utf8;
 
 #给表添加唯一索引
 alter table wechat_auth add unique index(open_id);
 
 create table headline(
 line_id int(10) primary key not null auto_increment,
 line_name varchar(50) default null,
 line_link varchar(50) not null,
 line_image varchar(50) not null,
 priority int(5) default null,
 enable_status int(5) default 0,
 create_time datetime default null,
 last_edit_time datetime default null
 )charset utf8;
 
 create table shop_category(
 shop_category_id int(10) primary key not null auto_increment,
 shop_category_name varchar(50) default null,
 shop_category_desc varchar(100) default null,
 shop_category_image varchar(50) default null,
 priority int(10) not null default 0,
 create_time datetime default null,
 last_edit_time datetime default null,
 parent_id int(10),
 foreign key(parent_id) references shop_category(shop_category_id)
 )charset utf8;
 
 create table shop(
 shop_id int(10) primary key not null auto_increment,
 shop_name varchar(50) not null,
 shop_desc varchar(200) not null,
 shop_addr varchar(50) not null,
 phone varchar(50) not null,
 shop_image varchar(50) not null,
 priority int(10) not null default 0,
 create_time datetime default null,
 last_edit_time datetime default null,
 enable_status int(5) not null default -1,
 advice varchar(200) default null,
 area_id int(10),
 user_id int(10),
 shop_category_id int(10),
 foreign key (area_id) references area(area_id),
 foreign key (shop_category_id) references shop_category(shop_category_id),
 foreign key (user_id) references person_info(user_id)
 )charset utf8;
 
 create table product_category(
 product_category_id int(10) primary key not null auto_increment,
 shop_id int(10),
 product_category_name varchar(50) not null,
priority int(10) not null default 0,
create_time datetime default null,
foreign key (shop_id) references shop(shop_id)
)charset utf8; 

create table product_image(
product_image_id int(10) primary key not null auto_increment,
image_addr varchar(50) not null,
image_desc varchar(100) not null,
priority int(10) not null default 0,
create_time datetime default null,
product_id int(10),
foreign key (product_id) references product(product_id)
)charset utf8;

create table product(
product_id int(10) primary key not null auto_increment,
product_name varchar(100) not null,
product_desc varchar(200) not null,
image_addr varchar(100) not null,
normal_price varchar(20) not null,
promotion_price varchar(20) default null,
create_time datetime default null,
last_edit_time datetime default null,
enable_status int(10) default -1,
product_category_id int(10),
shop_id int(10),
foreign key(product_category_id) references product_category(product_category_id),
foreign key (shop_id) references shop(shop_id)
)charset utf8;