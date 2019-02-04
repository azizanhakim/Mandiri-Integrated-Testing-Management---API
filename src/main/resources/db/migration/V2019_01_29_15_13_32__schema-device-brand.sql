create table device_brand (
  brand_id          varchar(64)  not null primary key,
  brand_name        varchar(150) not null,
  brand_description text
) engine = InnoDB;

insert into device_brand(brand_id, brand_name, brand_description)
values ('001', 'Huawei', 'Merek Huawei'),
       ('002', 'Asus', 'Merek Asus'),
       ('003', 'HP', 'Merek HP');
