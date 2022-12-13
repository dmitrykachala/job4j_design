CREATE TABLE customers(
                          id serial primary key,
                          first_name text,
                          last_name text,
                          age int,
                          country text
);

CREATE TABLE orders(
                       id serial primary key,
                       amount int,
                       customer_id int references customers(id)
);

INSERT INTO customers VALUES (1, 'Доу', 'Джон', 22, 'Msk'),
                                  (2, 'Грубер', 'Ганс', 33, 'Spb'),
                                  (3, 'Смит', 'Сара', 20, 'Ekb'),
                                  (4, 'Иванов', 'Иван', 18, 'Orel'),
                                  (5, 'Купер', 'Грета', 32, 'Kursk');

INSERT INTO orders VALUES (1, 1, 2),
                            (2, 2, 5),
                            (3, 3, 1);

SELECT * FROM customers WHERE customers.id NOT IN (SELECT customer_id FROM orders);