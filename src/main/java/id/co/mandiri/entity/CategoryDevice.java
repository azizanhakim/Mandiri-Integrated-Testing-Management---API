package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "device_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDevice {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "category_id", nullable = false, length = 64)
    private String category_id;

    @Column(name = "category_name", nullable = false, length = 150)
    private String category_name;

    @Lob
    @Type(type = "text")
    @Column(name = "category_description")
    private String category_description;
}
