create table engine(
    id serial primary key,
    model varchar(255)
);

create table car(
    id serial primary key,
    carModel varchar(255),
    engine_id int references engine(id)
);

insert into engine(model) values ('M54-TU2');
insert into car(carModel, engine_id) VALUES ('BMW E39', 1);

select * from car;

select * from engine where id in (select id from engine);