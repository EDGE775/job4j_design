create table car(
    id serial primary key,
    name varchar(255)
);

create table gosnumber(
    id serial primary key,
    number int
);

create table car_gosnumber(
    id serial primary key,
    car_id int references car(id) unique,
    gosnumber_id int references gosnumber(id) unique
);