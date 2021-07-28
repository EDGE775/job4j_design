select c.name as "Компания", count(*) as count from company as c join person as p 
	on c.id = p.company_id
	group by c.name
	order by count desc limit 1;