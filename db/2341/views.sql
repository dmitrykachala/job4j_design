create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);

insert into students (name) values ('Сидор Сидоров');
insert into students (name) values ('Вася Васильев');

insert into authors (name) values ('Виктор Пелевин');
insert into authors (name) values ('Сергей Лукьяненко');
insert into authors (name) values ('Борис Акунин');

insert into books (name, author_id) values ('Ночной позор', 4);
insert into books (name, author_id) values ('Дневной разврат', 4);
insert into books (name, author_id) values ('Сумеречный стыд', 4);
insert into books (name, author_id) values ('Поколение ы', 3);
insert into books (name, author_id) values ('Эдораст Пупыркин или Педуард Фанфаркин', 5);

insert into orders (book_id, student_id) values (6, 3);
insert into orders (book_id, student_id) values (7, 3);
insert into orders (book_id, student_id) values (8, 3);
insert into orders (book_id, student_id) values (9, 4);
insert into orders (book_id, student_id) values (10, 4);

select * from students;
select * from authors;
select * from books;

create view show_students_with_books_with_bl
    as select s.name as student, a.name as author, b.name from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
		 where b.name like '%ы%'
         group by (s.name, a.name, b.name);