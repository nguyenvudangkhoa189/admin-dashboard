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
