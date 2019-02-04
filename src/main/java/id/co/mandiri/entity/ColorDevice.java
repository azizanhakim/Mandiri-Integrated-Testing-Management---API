package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "device_color")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorDevice {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "color_id", nullable = false, length = 64)
    private String color_id;

    @Column(name = "color_name", nullable = false, length = 150)
    private String color_name;

    @Column(name = "color_code", nullable = false, length = 50)
    private String color_code;

    @Lob
    @Type(type = "text")
    @Column(name = "color_description")
    private String color_description;
}
