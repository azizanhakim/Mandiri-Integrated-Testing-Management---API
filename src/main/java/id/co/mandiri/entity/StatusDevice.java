package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "device_peminjaman")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDevice {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "peminjaman_id", nullable = false, length = 64)
    private String peminjaman_id;

    @Column(name = "peminjaman_status", nullable = false, length = 150)
    private String peminjaman_status;

}
