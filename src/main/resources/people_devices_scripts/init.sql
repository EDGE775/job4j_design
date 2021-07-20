insert into devices(name, price) values ('Iphone', 100000), ('Ipod', 20000), ('MacBook', 150000), ('HomePod', 50000);
insert into people(name) values ('Дима'), ('Маша'), ('Глаша'), ('Петя');

insert into devices_people(people_id, device_id) values (1, 1), (1, 2), (1, 3);
insert into devices_people(people_id, device_id) values (2, 2), (2, 3), (2, 4);
insert into devices_people(people_id, device_id) values (3, 1), (3, 3), (3, 4);
insert into devices_people(people_id, device_id) values (4, 1), (4, 2), (4, 4);