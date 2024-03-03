package com.booksort.writer;

import com.booksort.model.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class CSVWriterImpl implements Writer<Book> {
    @Override
    public void write(final Path path, final String[] headers, final List<Book> bookList) {
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.newFormat(';').withHeader(headers).withRecordSeparator("\n"));
            for (Book book : bookList) {
                csvPrinter.printRecord(book.getIsbn(), book.getName(), book.getAuthor().getAuthor(), book.getRelease());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
