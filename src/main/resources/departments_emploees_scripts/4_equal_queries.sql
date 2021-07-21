select * from departments as d left join emploees as e
	on e.department_id = d.id;
	
select * from emploees as e right join departments as d
	on e.department_id = d.id;