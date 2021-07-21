select * from departments as d left join emploees as e
	on e.department_id = d.id
	where e.id is null;