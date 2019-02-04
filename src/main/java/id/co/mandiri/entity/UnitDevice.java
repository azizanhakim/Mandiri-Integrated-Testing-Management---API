package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "device_unit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitDevice {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "unit_id", nullable = false, length = 64)
    private String unit_id;

    @Column(name = "unit_name", nullable = false, length = 150)
    private String unit_name;

}
