package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsolUI implements UI {
private final Scanner scanner;

    @Override
    public String input() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public void error(String message) {
        System.out.println("Error: " + message);
    }
}
