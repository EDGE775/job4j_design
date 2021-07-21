create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('Дима', 'муж');
insert into teens(name, gender) values ('Саша', 'муж');
insert into teens(name, gender) values ('Петя', 'муж');
insert into teens(name, gender) values ('Вася', 'муж');
insert into teens(name, gender) values ('Женя', 'муж');

insert into teens(name, gender) values ('Катя', 'жен');
insert into teens(name, gender) values ('Вика', 'жен');
insert into teens(name, gender) values ('Настя', 'жен');
insert into teens(name, gender) values ('Глаша', 'жен');
insert into teens(name, gender) values ('Наташа', 'жен');
insert into teens(name, gender) values ('Маша', 'жен');

select t1.name as "МУЖ", t2.name as "ЖЕН"
	from teens as t1 cross join teens as t2
	where t1.gender != t2.gender;