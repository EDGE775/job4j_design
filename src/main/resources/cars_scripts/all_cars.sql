select c.name as "Машина", b.name as "Кузов", e.name as "Двигатель", t.name as "КП"
	from car as c 
	left join body as b on c.body_id = b.id
	left join engine as e on c.engine_id = e.id
	left join transmission as t on c.transmission_id = t.id;