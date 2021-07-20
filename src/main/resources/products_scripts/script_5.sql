select t.name as "Тип", count(*) as "Кол-во" from type as t join product as p 
	on p.type_id = t.id
	group by t.name;