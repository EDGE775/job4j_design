create table home(
    id serial primary key,
    adress varchar(255)
);

create table peoples(
    id serial primary key,
    name varchar(255),
    home_id int references home(id)
);