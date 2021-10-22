create table car (
    id serial primary key, 
    model text,
	readyness boolean,
	modelYear date
);
insert into car (model, readyness, modelYear) values ('Nissan', TRUE, '2016-09-05');
select * from car;
update car set readyness = FALSE;
select * from car;
delete from car;
select * from car;