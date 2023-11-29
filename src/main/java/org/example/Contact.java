package org.example;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Builder
@Setter
@Getter
public class Contact {

    @NotNull
    @Size(min = 3, max = 40, message = "Name size must be between 3 and 40")
    private String fullName;

    @NotNull
    @Pattern(regexp = "^\\+\\d{11,13}$", message = "Phone number must match '^\\+\\d{11,13}$'")
    private String phoneNumber;

    @NotNull
    @Email(message = "Invalid email format")
    private String email;

    @Override
    public String toString() {
        return MessageFormat.format("{0} | {1} | {2}", fullName, phoneNumber, email);
    }
}
