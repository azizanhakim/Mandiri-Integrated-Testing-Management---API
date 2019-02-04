create table device_condition (
  condition_id          varchar(64)  not null primary key,
  condition_status        varchar(150) not null
) engine = InnoDB;

insert into device_condition(condition_id, condition_status)
values ('001', 'Baik'),
       ('002', 'Kurang Baik'),
       ('003', 'Tidak Baik');
