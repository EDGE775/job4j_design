create table body(
	id serial primary key,
	name varchar(255)
);

create table engine(
	id serial primary key,
	name varchar(255)
);

create table transmission(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(name) values ('Седан'), ('Купе'), ('Хетчбек'), ('Универсал');

insert into engine(name) values ('Бензиновый'), ('Дизель'), ('Гибрид');

insert into transmission(name) values ('Механика'), ('Автомат'), ('Робот');

insert into car(name, body_id, engine_id, transmission_id) values
	('Ford Fusion', 4, 1, 1),
	('KIA Rio', 1, 2, 2),
	('Nissan Almera', 3, 1, 1),	
	('Infiniti cx50', 3, 2, 2),	
	('BMW M5', 1, 2, 3),	
	('Волга', 1, 1, 1);