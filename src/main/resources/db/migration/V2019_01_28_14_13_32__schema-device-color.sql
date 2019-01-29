create table device_color (
  id          varchar(64)  not null primary key,
  name        varchar(150) not null,
  code        varchar(100) not null,
  description text
) engine = InnoDB;

insert into device_color(id, name, code, description)
values ('001', 'Merah', '#E80412','Warna Merah'),
       ('002', 'Kuning', '#F0C600','Warna Kuning'),
       ('003', 'Silver', '#525D61','Warna Silver');
