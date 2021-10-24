create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values ('Жора'), ('Гога'), ('Иннокентий');
insert into devices(name, price) values ('PC', 105000), ('УтюХ', 3000), ('Пылесос', 12000), 
('ПММ', 21000), ('Стиральная машина', 23000);
insert into devices_people(device_id, people_id) values (1, 1), (3, 1), (5, 1);
insert into devices_people(device_id, people_id) values (2, 2), (4, 2);
insert into devices_people(device_id, people_id) values (1, 3);

select avg(price) from devices;
select dp.people_id, avg(d.price) from devices as d join devices_people as dp on 
dp.device_id = d.id group by dp.people_id;
select dp.people_id, avg(d.price) from devices as d join devices_people as dp on 
dp.device_id = d.id group by dp.people_id having avg(d.price) > 50000;