package vu.lt.services.validator;

import vu.lt.entities.ContactInfo;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@ApplicationScoped
@Specializes
public class LithuanianContactInfoValidator extends ContactInfoValidator{
    public void validate(ContactInfo contactInfo) {
        if (!contactInfo.getCountry().equalsIgnoreCase("Lithuania")) {
            throw new IllegalArgumentException("Invalid country");
        }
        if (!contactInfo.getApartmentNo().matches("^\\d+$")) {
            throw new IllegalArgumentException("Invalid apartment number");
        }
    };
}
