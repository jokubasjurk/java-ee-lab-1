package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({@NamedQuery(
        name = "ContactInfo.findAll",
        query = "select a from ContactInfo as a"
)})
@Table(
        name = "CONTACT_INFO"
)
@Getter @Setter
public class ContactInfo implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Size(
            max = 50
    )
    @Column(
            name = "STREET"
    )
    private String street;
    @Size(
            max = 50
    )
    @Column(
            name = "CITY"
    )
    private String city;
    @Size(
            max = 50
    )
    @Column(
            name = "COUNTRY"
    )
    private String country;
    @Size(
            max = 50
    )
    @Column(
            name = "APARTMENT_NO"
    )
    private String apartmentNo;
    @OneToOne
    @JoinColumn(
            name = "AUTHOR_ID"
    )
    private Author author;
    @Version
    @Column(
            name = "OPT_LOCK_VERSION"
    )
    private Integer version;

    public ContactInfo() {
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ContactInfo contactInfo = (ContactInfo)o;
            return Objects.equals(id, contactInfo.id) && Objects.equals(street, contactInfo.street);
        } else {
            return false;
        }
    }

    public String toString() {
        return "city='" + city + "', country='" + country + "', street='" + street + "', apartmentNo='" + apartmentNo + "'";
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.street});
    }
}
