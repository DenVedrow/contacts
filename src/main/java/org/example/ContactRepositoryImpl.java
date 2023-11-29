package org.example;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ContactRepositoryImpl implements ContactRepository {
    private Map<String, Contact> contacts;

    @PostConstruct
    public void init() {
        this.contacts = new HashMap<>();
    }

    @Override
    public void save(Contact contact) {
        this.contacts.put(contact.getEmail(), contact);
    }

    @Override
    public void saveAll(Map<String, Contact> contacts) {
        this.contacts.putAll(contacts);
    }

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(this.contacts.values());
    }

    @Override
    public Contact deleteByEmail(String email) {
        return this.contacts.remove(email);
    }
}
