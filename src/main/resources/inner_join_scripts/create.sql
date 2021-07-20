create table home(
    id serial primary key,
    adress varchar(255)
);

create table peoples(
    id serial primary key,
    name varchar(255),
    home_id int references home(id)
);

insert into home(adress) values ('Юности 7');
insert into home(adress) values ('Юности 8');
insert into home(adress) values ('Юности 9');
insert into home(adress) values ('Юности 10');
insert into home(adress) values ('Юности 11');

insert into peoples(name, home_id) values ('Ivan', 1);
insert into peoples(name, home_id) values ('Ivan1', 1);
insert into peoples(name, home_id) values ('Ivan2', 2);
insert into peoples(name, home_id) values ('Ivan3', 3);
insert into peoples(name, home_id) values ('Ivan4', 4);
insert into peoples(name, home_id) values ('Ivan5', 5);
insert into peoples(name, home_id) values ('Ivan6', 2);
insert into peoples(name, home_id) values ('Ivan7', 3);
insert into peoples(name, home_id) values ('Ivan8', 4);