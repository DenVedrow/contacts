package org.example;

import jakarta.validation.constraints.Email;

import java.io.IOException;
import java.util.List;

public interface ContactService {

    List<String> getAll();

    void add(String line);

    Contact removeByEmail(@Email(message = "Invalid email format") String email);

    void saveToFile() throws IOException;

    void insertInitData();
}
