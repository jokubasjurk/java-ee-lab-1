package vu.lt.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import vu.lt.entities.ContactInfo;

@ApplicationScoped
public class ContactInfoDAO {
    @Inject
    private EntityManager em;

    public ContactInfoDAO() {
    }

    public ContactInfo findOne(Integer id) {
        return (ContactInfo)this.em.find(ContactInfo.class, id);
    }

    public void persist(ContactInfo contactInfo) {
        this.em.persist(contactInfo);
    }
}
