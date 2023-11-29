package org.example;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final Validator validator;
    private final FileManager fileManager;
    private final ContactRepository contactRepository;

    @Value("${app.files.src}")
    private String srcFile;

    @Value("${app.files.dst}")
    private String dstFile;

    @Override
    public List<String> getAll() {
        return contactRepository.findAll()
                .stream()
                .map(Contact::toString)
                .toList();
    }

    @Override
    public void add(String line) {
        contactRepository.save(buildContact(line));
    }

    @Override
    public Contact removeByEmail(String email) {
        return contactRepository.deleteByEmail(email);
    }

    @Override
    public void saveToFile() throws IOException {
        fileManager.writeAll(dstFile, getAll());
    }

    @Override
    public void insertInitData() {
        Map<String, Contact> contactCollection = new HashMap<>();
        List<String> lines;

        try {
            lines = fileManager.readAll(srcFile);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        for (int i = 0; i < lines.size(); i++) {
            try {
                var contact = buildContact(lines.get(i));
                contactCollection.put(contact.getEmail(), contact);
            } catch (ConstraintViolationException e) {
                var message = MessageFormat.format(
                        "Invalid initial file format, line: {0}, errors: {1}", i, e.getMessage());
                throw new RuntimeException(message);
            }
        }

        contactRepository.saveAll(contactCollection);
    }

    private Contact buildContact(String line) {
        var values = line.split(";", 3);
        var contact = Contact
                .builder()
                .fullName(values[0])
                .phoneNumber(values[1])
                .email(values[2])
                .build();

        var violations = validator.validate(contact);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return contact;
    }
}
