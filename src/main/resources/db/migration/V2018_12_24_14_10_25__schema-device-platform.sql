create table device_category (
  category_id          varchar(64)  not null primary key,
  category_name        varchar(150) not null,
  category_description text
) engine = InnoDB;

