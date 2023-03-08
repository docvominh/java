CREATE TABLE product
(
    id          IDENTITY NOT NULL PRIMARY KEY,
    category    VARCHAR(100),
    name        VARCHAR(100),
    manufacture VARCHAR(50),
    price       REAL
);

INSERT INTO product(name, category, manufacture, price)
values ('Dell xps 13', 'computer', 'Dell', 1500.50);
INSERT INTO product(name, category, manufacture, price)
values ('Dell xps 15', 'computer', 'Dell', 1500);
INSERT INTO product(name, category, manufacture, price)
values ('Dell xps 17', 'computer', 'Dell', 1700);
INSERT INTO product(name, category, manufacture, price)
values ('Mac Book pro M1', 'computer', 'Apple', 1600);
INSERT INTO product(name, category, manufacture, price)
values ('Mac Book pro M2', 'computer', 'Apple', 1800);
INSERT INTO product(name, category, manufacture, price)
values ('G-Phone', 'mobile', 'Google', 900.30);