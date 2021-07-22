select * from engine as e 
	left join car as c on c.engine_id = e.id
	where c.id is null;