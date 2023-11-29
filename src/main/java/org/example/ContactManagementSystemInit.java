package org.example;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("init")
@Primary
public class ContactManagementSystemInit extends ContactManagementSystem {


    public ContactManagementSystemInit(ContactService contactService, UI ui) {
        super(contactService, ui);
    }

    @PostConstruct
    public void init() {
        contactService.insertInitData();
    }
}
