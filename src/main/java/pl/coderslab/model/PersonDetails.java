package pl.coderslab.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "persons_details")
public class PersonDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "street_number")
    private Integer streetNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
