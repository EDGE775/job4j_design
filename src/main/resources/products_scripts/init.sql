insert into type(name) values ('СЫР'), ('МОЛОКО'), ('МОРОЖЕНОЕ'), ('МЯСО');

insert into product(name, type_id, expired_date, price) 
	values('Сыр плавленный', 1, '30.07.2021', 50);
insert into product(name, type_id, expired_date, price) 
	values('Сыр моцарелла', 1, '19.07.2021', 150);
insert into product(name, type_id, expired_date, price) 
	values('Сыр пармезан', 1, '19.09.2021', 300);
	
insert into product(name, type_id, expired_date, price) 
	values('Молоко домик в деревне', 2, '25.07.2021', 60);
insert into product(name, type_id, expired_date, price) 
	values('Молоко весёлый молочник', 2, '25.08.2021', 70);
	
insert into product(name, type_id, expired_date, price) 
	values('Мороженое пломбир', 3, '25.08.2021', 50);
insert into product(name, type_id, expired_date, price) 
	values('Мороженое эскимо', 3, '15.07.2021', 50);
	
insert into product(name, type_id, expired_date, price) 
	values('Мясо курицы', 4, '19.07.2021', 200);
insert into product(name, type_id, expired_date, price) 
	values('Стейк мираторг', 4, '25.07.2021', 400);
insert into product(name, type_id, expired_date, price) 
	values('Котлеты', 4, '30.07.2021', 300);