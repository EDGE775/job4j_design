select * from transmission as t 
	left join car as c on c.transmission_id = t.id
	where c.id is null;