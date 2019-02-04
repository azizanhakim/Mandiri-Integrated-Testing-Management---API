create table device_unit (
  unit_id          varchar(64)  not null primary key,
  unit_name        varchar(150) not null
) engine = InnoDB;

insert into device_unit(unit_id, unit_name)
values ('001', 'Pcs'),
       ('002', 'Meter'),
       ('003', 'Centimeter'),
       ('004', 'Kilogram'),
       ('005', 'Gram');
