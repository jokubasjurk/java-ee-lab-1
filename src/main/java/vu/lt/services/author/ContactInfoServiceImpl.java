package vu.lt.services.author;

import vu.lt.entities.Author;
import vu.lt.entities.ContactInfo;
import vu.lt.persistence.ContactInfoDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ContactInfoServiceImpl implements ContactInfoService {
    @Inject
    private ContactInfoDAO contactInfoDAO;
    @Override
    public void createContactInfo(Author author, ContactInfo contactInfoToCreate) {
        contactInfoToCreate.setAuthor(author);
        contactInfoDAO.persist(contactInfoToCreate);
    }
}
