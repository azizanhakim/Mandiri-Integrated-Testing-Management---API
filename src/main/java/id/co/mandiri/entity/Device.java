package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "device")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "device_id", nullable = false, length = 64)
    private String device_id;

    @Column(name = "device_name", nullable = false, length = 150)
    private String device_name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryDevice categoryDevice;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private ColorDevice colorDevice;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandDevice brandDevice;

    @ManyToOne
    @JoinColumn(name = "condition_id", nullable = false)
    private ConditionDevice conditionDevice;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private UnitDevice unitDevice;

    @ManyToOne
    @JoinColumn(name = "peminjaman_id", nullable = false)
    private StatusDevice statusDevice;

//    @Column(name = "name", nullable = false, length = 150)
//    private String name;
//
//    @Column(name = "category_id", nullable = false)
//    private String category_id;
//
//    @Column(name = "color_id", nullable = false)
//    private String color_id;
//
//    @Column(name = "brand_id", nullable = false)
//    private String brand_id;
//
//    @Column(name = "condition_id", nullable = false)
//    private String condition_id;
//
//    @Column(name = "unit_id", nullable = false)
//    private String unit_id;
//
//    @Column(name = "peminjaman_id", nullable = false)
//    private String peminjaman_id;


}
