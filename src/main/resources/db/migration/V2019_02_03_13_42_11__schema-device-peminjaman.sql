create table device_peminjaman (
  peminjaman_id          varchar(64)  not null primary key,
  peminjaman_status        varchar(150) not null
) engine = InnoDB;

insert into device_peminjaman(peminjaman_id, peminjaman_status)
values ('001', 'Tersedia'),
       ('002', 'Sedang dipinjam');

alter table device
  add constraint fk_device_peminjaman foreign key (peminjaman_id)
    references device_peminjaman (peminjaman_id) on update cascade on delete restrict;
