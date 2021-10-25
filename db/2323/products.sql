create table type(
	id serial primary key, 
	name varchar(255)
);

create table product(
	id serial primary key, 
	name varchar(255), 
	type_id int references type(id), 
	expired_date date, 
	price float
);

insert into type(name) values ('сыр'), ('молоко'), ('мороженое'), ('мясо'), ('рыба'), ('хлеб'), 
('консервы'), ('фрукты');
select * from type;
insert into product(name, type_id, expired_date, price) values ('сырок', 2, '2021-11-02', 57.6), 
('пармезан', 1, '2021-12-02', 990.7), ('мороженое клубничное', 3, '2022-11-02', 115), 
('мороженое сердце', 4, '2021-11-29', 250), ('рыба копченая', 5, '2021-11-15', 350),
('фондю', 1, '2021-12-21', 230), ('консервы рыбные', 7, '2024-11-02', 150), 
('батон нарезной', 6, '2021-10-28', 35), ('яблоки', 8, '2021-11-27', 79);
select * from product;

select t.name as type, p.name, p.price from product as p, type as t where t.name = 'сыр' and p.type_id = t.id;

select * from product where name like '%мороженое%';

insert into product(name, type_id, expired_date, price) values ('молоко', 2, '2021-10-20', 67.6);
select * from product where expired_date < current_date;

select * from product as p where p.price = (select max(price) from product);

select t.name, count(p.type_id) as count from product as p, type as t where p.type_id = t.id group by t.name;

select t.id, p.name, p.price from product as p, type as t where p.type_id = t.id and (t.name ='сыр' 
or t.name ='молоко') order by t.id;

select t.id, t.name, count(p.type_id) as count from product as p, type as t where p.type_id = t.id 
group by t.id having count(p.type_id) < 10 order by t.id;

select t.name as type, p.name, p.price from product as p, type as t where p.type_id = t.id 
order by t.name;