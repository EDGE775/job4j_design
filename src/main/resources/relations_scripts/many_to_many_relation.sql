 create table product(
     id serial primary key,
     name varchar(255)
 );

 create table shop(
     id serial primary key,
     name varchar(255)
 );

 create table product_shop(
     id serial primary key,
     product_id int references product(id),
     shop_id int references shop(id)
 );