create table engine(
	id serial primary key,
	model text
);

create table body(
	id serial primary key,
	type text
);

create table gearbox(
	id serial primary key,
	type text
);

create table car(
	id serial primary key,
	name text,
	engine_id int references engine(id),
	body_id int references body(id),
	gearbox_id int references gearbox(id)
);

insert into engine(model) values ('CAXA'), ('B47'), ('B38'), ('2UZ-FE'), ('1KR-FE');
insert into body(type) values ('3 door'), ('5 door'), ('sedan'), ('combi'), ('roadster');
insert into gearbox(type) values ('MT'), ('AT'), ('CVT'), ('RT');

select * from car;

insert into car(name, engine_id, body_id, gearbox_id) values ('Citroen C1', 5, 2, 4),
('BMW 1er', 2, 1, 2), ('VW Golf', 1, 1, 2), ('Toyota LC', 4, 4, 2);

select c.name, e.model, b.type, g.type from car c left join engine e on c.engine_id = e.id
	left join body b on c.body_id = b.id
		left join gearbox g on c.gearbox_id = g.id;

select * from engine e left join car c on c.engine_id = e.id where c.id is null;
select * from body b left join car c on c.body_id = b.id where c.id is null;
select * from gearbox g left join car c on c.gearbox_id = g.id where c.id is null;
