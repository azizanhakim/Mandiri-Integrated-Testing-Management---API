create table device_brand (
  id          varchar(64)  not null primary key,
  name        varchar(150) not null,
  description text
) engine = InnoDB;

insert into device_brand(id, name, description)
values ('001', 'Huawei', 'Merek Huawei'),
       ('002', 'Asus', 'Merek Asus'),
       ('003', 'HP', 'Merek HP');
