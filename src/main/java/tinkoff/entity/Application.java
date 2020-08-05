package tinkoff.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "applications")
public class Application {

    @Id
    @GeneratedValue
    @Column(name = "APPLICATION_ID")
    private Integer applicationId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "DT_CREATED")
    private Date created;

    @ManyToMany(mappedBy = "applications")
    @JsonIgnoreProperties("applications")
    private List<Contact> contacts;

}
