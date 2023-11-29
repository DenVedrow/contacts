package org.example;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class FileManagerImpl implements FileManager {

    @Override
    public List<String> readAll(String src) throws IOException {
        return Files.readAllLines(Path.of(src));
    }

    @Override
    public void writeAll(String dst, List<String> lines) throws IOException {
        var path = Path.of(dst);
        Files.createDirectories(path.getParent());
        Files.write(path, lines);
    }
}
