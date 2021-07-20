select h.adress || ' ' || p.name as "Адрес и жилец" 
from home as h join peoples as p on p.home_id = h.id;