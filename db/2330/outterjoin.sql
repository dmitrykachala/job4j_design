create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

create table teens(
    id serial primary key,
    name varchar(255),
    gender char
);

insert into departments(name) values ('логистика'), ('кадры'), ('финансы'), ('поддержка'), ('разработка');

insert into employees(name, department_id) values ('Света', 1), ('Коля', 5), ('Петя', 5), 
('Вася', 4), ('Оля', 3), ('Иосиф', 4);

insert into teens(name, gender) values ('Кеша', 'м'), ('Изя', 'м'), ('Роза', 'ж'),
('Глаша', 'ж'), ('Даздраперма', 'ж'), ('Роскомпарт', 'м');

select * from departments as d left join employees as e on d.id = e.department_id;
select * from departments as d right join employees as e on d.id = e.department_id;
select * from employees as e full join departments as d on d.id = e.department_id;
select * from departments cross join employees;

select * from departments as d left join employees as e on d.id = e.department_id where e.department_id isnull;

select * from departments as d right join employees as e on d.id = e.department_id;
select * from employees as e left join departments as d on d.id = e.department_id;

select * from (select t.name, t.gender from teens t where t.gender = 'м') as mt 
cross join (select t.name, t.gender from teens t where t.gender = 'ж') as ft;