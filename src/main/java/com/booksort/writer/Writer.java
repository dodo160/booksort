package com.booksort.writer;

import java.nio.file.Path;
import java.util.List;

public interface Writer<T> {

    void write(Path path, String[] headers, List<T> list);
}
