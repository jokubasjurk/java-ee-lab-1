package vu.lt.decorators;

import vu.lt.entities.Author;
import vu.lt.entities.ContactInfo;
import vu.lt.services.author.ContactInfoService;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class ContactInfoDecorator implements ContactInfoService {

    @Delegate
    @Any
    @Inject ContactInfoService contactInfoService;
    @Override
    public void createContactInfo(Author author, ContactInfo contactInfoToCreate) {
        if ("Russia".equalsIgnoreCase(contactInfoToCreate.getCountry())) {
            throw new IllegalArgumentException("Our site has suspended operations with russian content creators");
        }
        contactInfoService.createContactInfo(author, contactInfoToCreate);
    }
}
