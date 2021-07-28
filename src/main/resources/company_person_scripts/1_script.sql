select p.name as "Person", c.name as "Company"
	from person as p 
	left join company as c on c.id = p.company_id
	where c.id != 5;