package vu.lt.usecases;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Author;
import vu.lt.entities.ContactInfo;
import vu.lt.persistence.AuthorsDAO;
import vu.lt.persistence.ContactInfoDAO;

@Model
public class AuthorsDetails {
    @Inject
    private AuthorsDAO authorsDAO;
    @Inject
    private ContactInfoDAO contactInfoDAO;

    @Setter @Getter
    private ContactInfo contactInfoToCreate = new ContactInfo();
    @Setter @Getter
    private Author author;

    public AuthorsDetails() {
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer authorId = Integer.parseInt((String)requestParameters.get("authorId"));
        author = authorsDAO.findOne(authorId);
    }

    @Transactional
    public String createContactInfo() {
        if (author.getContactInfo() != null) {
            return "/authors.xhtml?faces-redirect=true&authorId=" + author.getId() + "&error=author-contact-info-exists-exception";
        } else {
            contactInfoToCreate.setAuthor(author);
            contactInfoDAO.persist(contactInfoToCreate);
            return "authors.xhtml?authorId=" + author.getId() + "&faces-redirect=true";
        }
    }
}
