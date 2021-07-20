-- Создаём таблицу ролей для пользователей
 create table roles(
     id serial primary key,
     name varchar(255)
 );

--Создаём таблицу правил для ролей
 create table rules(
     id serial primary key,
     name varchar(255)
 );

--Связываем роли и правила по принципу many-to-many
 create table roles_rules(
     id serial primary key,
     roles_id int references roles(id),
     rules_id int references rules(id)
 );

--Создаём таблицу пользователей и связываем с ролями
create table users(
    id serial primary key,
    name varchar(255),
    role_id int references roles(id)
);

--Создаём таблицу состояний заявки
 create table states(
     id serial primary key,
     name varchar(255)
 );

--Создаём таблицу категорий заявки
 create table categorys(
     id serial primary key,
     name varchar(255)
 );

--Создаём таблицу заявок и связываем её с таблицами
--пользователей, категориями заявок и состояниями заявок
 create table items(
     id serial primary key,
     name varchar(255),
     user_id int references users(id),
     category_id int references categorys(id),
     state_id int references states(id),
 );

--Создаём таблицу комментариев к заявкам
create table comments(
    id serial primary key,
    name varchar(255),
    item_id int references items(id)
);

--Создаём таблицу приложений к заявкам
create table attachs(
    id serial primary key,
    name varchar(255),
    item_id int references items(id)
);