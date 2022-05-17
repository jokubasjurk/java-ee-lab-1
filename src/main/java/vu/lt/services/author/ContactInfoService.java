package vu.lt.services.author;

import vu.lt.entities.Author;
import vu.lt.entities.ContactInfo;

public interface ContactInfoService {

    void createContactInfo(Author author, ContactInfo contactInfoToCreate);
}
