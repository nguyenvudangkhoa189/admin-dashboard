DROP TABLE IF EXISTS orders;
create table orders (
    RecordID int (11),
    OrderID int (11),
    Country varchar (191),
    ShipCity varchar (191),
    CompanyAgent varchar (191),
    ShipDate datetime ,
    Status varchar (191),
    Type varchar (191)
);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(127),
    summary VARCHAR(1023),
    detail TEXT
);

DROP TABLE IF EXISTS category;
CREATE TABLE category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(127),
    summary VARCHAR(1023),
    detail TEXT,
    x INT,
    y INT,
    w INT,
    h INT
)