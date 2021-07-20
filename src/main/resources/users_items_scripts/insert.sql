--Заполняем роли
insert into roles(name) values ('Admin');
insert into roles(name) values ('User');
insert into roles(name) values ('Programmer');

--Заполняем права
insert into rules(name) values ('Доступ к диску С');
insert into rules(name) values ('Доступ к диску Х');
insert into rules(name) values ('Права администратора');

--Присваиваем ролям - права
insert into roles_rules(role_id, rule_id) values (1, 1);
insert into roles_rules(role_id, rule_id) values (1, 2);
insert into roles_rules(role_id, rule_id) values (1, 3);
insert into roles_rules(role_id, rule_id) values (2, 1);
insert into roles_rules(role_id, rule_id) values (3, 2);

--Добавляем пользователей и связываем их с ролями
insert into users(name, role_id) values ('Дима', 1);
insert into users(name, role_id) values ('Женя', 2);
insert into users(name, role_id) values ('Глаша', 3);

--Заполняем категории заявок
insert into categorys(name) values ('Важная!');
insert into categorys(name) values ('Очень важная!');
insert into categorys(name) values ('Супер важная!');

--Заполняем статусы заявок
insert into states(name) values ('В работе');
insert into states(name) values ('Выполняется');
insert into states(name) values ('Закончена');

--Добавляем заявки и связываем их с пользователями, категориями и статусами
insert into items(name, user_id, category_id, state_id) values ('Всё починить!', 2, 1, 1);
insert into items(name, user_id, category_id, state_id) values ('Установить софт', 3, 3, 2);
insert into items(name, user_id, category_id, state_id) values ('Купить новый стул', 3, 2, 3);

--Добавляем комментарии к заявкам
insert into comments(name, item_id) values ('Сломались кнопки', 1);
insert into comments(name, item_id) values ('Установить JDK!', 2);
insert into comments(name, item_id) values ('Стул с колёсиками', 3);

--Добавляем приложения к заявкам
insert into attachs(name, item_id) values ('Фото 1', 1);
insert into attachs(name, item_id) values ('Фото 2', 2);
insert into attachs(name, item_id) values ('Фото 3', 3);
