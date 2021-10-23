create table inn(
    id serial primary key,
    number int
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table inn_people(
    id serial primary key,
    inn_id int references inn(id) unique,
    people_id int references people(id) unique
);