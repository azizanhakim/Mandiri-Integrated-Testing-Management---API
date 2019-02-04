package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "device_brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDevice {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "brand_id", nullable = false, length = 64)
    private String brand_id;

    @Column(name = "brand_name", nullable = false, length = 150)
    private String brand_name;

    @Lob
    @Type(type = "text")
    @Column(name = "brand_description")
    private String brand_description;
}
