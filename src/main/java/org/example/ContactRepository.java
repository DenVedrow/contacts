package org.example;

import java.util.List;
import java.util.Map;

public interface ContactRepository {

    void save(Contact contact);

    void saveAll(Map<String, Contact> contacts);

    List<Contact> findAll();

    Contact deleteByEmail(String email);
}
