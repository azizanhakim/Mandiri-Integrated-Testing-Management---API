create table device (
  device_id            varchar(64)  not null primary key,
  device_name          varchar(150) not null,
  category_id   varchar(64) not null,
  color_id      varchar(64) not null,
  brand_id      varchar(64) not null,
  condition_id  varchar(64) not null,
  unit_id       varchar(64) not null,
  peminjaman_id varchar(64) not null
) engine = InnoDB;

insert into device(device_id, device_name, category_id, color_id, brand_id, condition_id, unit_id, peminjaman_id)
values ('001', 'Something', '002', '003', '001', '003', '005', '001'),
       ('002', 'Im confuse', '001', '001', '002', '001', '001', '002');

alter table device
  add constraint fk_device_category foreign key (category_id)
    references device_category (category_id) on update cascade on delete restrict;

alter table device
  add constraint fk_device_color foreign key (color_id)
    references device_color (color_id) on update cascade on delete restrict;

alter table device
  add constraint fk_device_brand foreign key (brand_id)
    references device_brand (brand_id) on update cascade on delete restrict;

alter table device
  add constraint fk_device_condition foreign key (condition_id)
    references device_condition (condition_id) on update cascade on delete restrict;

alter table device
  add constraint fk_device_unit foreign key (unit_id)
    references device_unit (unit_id) on update cascade on delete restrict




