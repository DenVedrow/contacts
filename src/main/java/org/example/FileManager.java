package org.example;

import java.io.IOException;
import java.util.List;

public interface FileManager {

    List<String> readAll(String src) throws IOException;

    void writeAll(String dst, List<String> lines) throws IOException;
}
