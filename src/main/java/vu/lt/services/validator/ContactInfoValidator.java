package vu.lt.services.validator;

import vu.lt.entities.ContactInfo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContactInfoValidator {
    public void validate(ContactInfo contactInfo) {
        if (!contactInfo.getApartmentNo().matches("^\\d+$")) {
            throw new IllegalArgumentException("Invalid apartment number");
        }
    };
}
