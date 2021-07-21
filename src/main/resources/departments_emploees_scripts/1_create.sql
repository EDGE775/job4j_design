create table departments(
    id serial primary key,
    name varchar(255)
);

create table emploees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('Department 1');
insert into departments(name) values ('Department 2');
insert into departments(name) values ('Department 3');
insert into departments(name) values ('Department 4');

insert into emploees(name, department_id) values ('Emploueer 1', 1);
insert into emploees(name, department_id) values ('Emploueer 2', 2);
insert into emploees(name, department_id) values ('Emploueer 3', 2);
insert into emploees(name, department_id) values ('Emploueer 4', null);
insert into emploees(name, department_id) values ('Emploueer 5', null);
insert into emploees(name, department_id) values ('Emploueer 6', 1);