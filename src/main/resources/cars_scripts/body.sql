select * from body as b 
	left join car as c on c.body_id = b.id
	where c.id is null;