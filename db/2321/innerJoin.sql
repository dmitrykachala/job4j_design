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
insert into engine(model) values ('CAXA');
insert into engine(model) values ('2JZ-GTE');
insert into engine(model) values ('N55');
insert into engine(model) values ('SR20-DET');
insert into car(carModel, engine_id) VALUES ('BMW E39', 1);
insert into car(carModel, engine_id) VALUES ('BMW E46', 1);
insert into car(carModel, engine_id) VALUES ('Crown', 3);
insert into car(carModel, engine_id) VALUES ('Chaser', 3);
insert into car(carModel, engine_id) VALUES ('Golf', 2);
insert into car(carModel, engine_id) VALUES ('Jetta', 2);
insert into car(carModel, engine_id) VALUES ('Golf Plus', 2);
insert into car(carModel, engine_id) VALUES ('BMW X6', 4);
insert into car(carModel, engine_id) VALUES ('Silvia', 5);

select c.carModel, e.model from car as c join engine as e on c.engine_id = e.id and e.model = 'N55';
select e.model, c.carModel from car as c join engine as e on c.engine_id = e.id and c.carModel like 'C%';
select c.carModel, e.model from car as c join engine as e on c.engine_id = e.id and e.model like '%2%';
