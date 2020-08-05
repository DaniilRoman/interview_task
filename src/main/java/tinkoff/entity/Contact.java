package tinkoff.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue
    @Column(name = "CONTACT_ID")
    private Integer contactId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "contacts_applications",
            joinColumns = { @JoinColumn(name = "CONTACT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "APPLICATION_ID") }
    )
    @JsonIgnoreProperties("contacts")
    private List<Application> applications;

}
