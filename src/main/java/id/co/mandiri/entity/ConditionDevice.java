package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "device_condition")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionDevice {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "condition_id", nullable = false, length = 64)
    private String condition_id;

    @Column(name = "condition_status", nullable = false, length = 150)
    private String condition_status;

}
