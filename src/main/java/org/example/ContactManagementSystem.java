package org.example;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;

@Component
@Profile("default")
@RequiredArgsConstructor
public class ContactManagementSystem {
    protected final ContactService contactService;
    protected final UI ui;

    private String WELCOME_MESSAGE = "*** - Contact management system - ***";
    private String HELP_MESSAGE = """ 
                list - get contact list
                add <fullName;phoneNumber;email> - add contact
                del <email> - delete contact by email
                save - save contacts list to file
                exit - exit
            """;

    public void run() {
        ui.info(WELCOME_MESSAGE);
        ui.info(HELP_MESSAGE);

        while (true) {
            var input = ui.input().split(" ", 2);

            if ("exit".equals(input[0])) {
                break;
            }

            switch (input[0]) {
                case "list" -> {
                    var contacts = contactService.getAll();
                    ui.info(contacts.toString());
                }

                case "add" -> {
                    try {
                        contactService.add(input[1]);
                        ui.info("Ok");
                    } catch (ConstraintViolationException e) {
                        ui.error("Invalid arguments format: " + e.getMessage());
                    }
                }

                case "del" -> {
                    try {
                        var contact = contactService.removeByEmail(input[1]);
                        if (contact == null) {
                            ui.error(MessageFormat.format("{0} not found", input[1]));
                            continue;
                        }
                        ui.info(MessageFormat.format("<{0}> successfully deleted", contact.toString()));
                    } catch (ConstraintViolationException e) {
                        ui.error(e.getMessage().split(": ")[1]);
                    }
                }

                case "save" -> {
                    try {
                        contactService.saveToFile();
                        ui.info("Ok");
                    } catch (IOException e) {
                        ui.error(e.getMessage());
                    }
                }

                case "help" -> {
                    ui.info(HELP_MESSAGE);
                }

                default -> {
                    ui.error(MessageFormat.format("Invalid command {0}", input[0]));
                    ui.info(HELP_MESSAGE);
                }
            }
        }
    }
}
