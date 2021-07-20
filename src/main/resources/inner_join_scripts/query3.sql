select h.adress as "Адрес дома", p.name as "Имя" 
from home as h join peoples as p on p.home_id = h.id and h.adress = 'Юности 9';