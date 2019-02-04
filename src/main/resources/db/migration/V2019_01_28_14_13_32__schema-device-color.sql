create table device_color (
  color_id          varchar(64)  not null primary key,
  color_name        varchar(150) not null,
  color_code        varchar(100) not null,
  color_description text
) engine = InnoDB;

insert into device_color(color_id, color_name, color_code, color_description)
values ('001', 'Merah', '#E80412','Warna Merah'),
       ('002', 'Kuning', '#F0C600','Warna Kuning'),
       ('003', 'Silver', '#525D61','Warna Silver');
