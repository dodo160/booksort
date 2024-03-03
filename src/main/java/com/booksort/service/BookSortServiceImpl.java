package com.booksort.service;

import com.booksort.model.Book;
import com.booksort.model.BookList;
import com.booksort.writer.Writer;
import com.booksort.xml.marshaller.XmlMarshaller;
import com.booksort.xml.validator.XmlValidator;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@Service
public class BookSortServiceImpl implements BookSortService {

    private static final String[] HEADERS = {"ISBN", "Nazev", "Autor", "Vydano"};
    private static final  String FILENAME_OLD_BOOK = "\\knihy_stare.csv";
    private static final  String FILENAME_NEW_BOOK = "\\knihy_nove.csv";
    private Writer<Book> csvWriter;
    private XmlValidator xmlValidator;
    private XmlMarshaller xmlMarshaller;

    public BookSortServiceImpl(Writer<Book> csvWriter, XmlValidator xmlValidator, XmlMarshaller xmlMarshaller) {
        this.csvWriter = csvWriter;
        this.xmlValidator = xmlValidator;
        this.xmlMarshaller = xmlMarshaller;
    }

    @Override
    public void sort(final String path, final int year) {
        xmlValidator.validateXml(path);
        final BookList bookList = (BookList) xmlMarshaller.fromXmlFile(BookList.class, path);
        final String pathToFolder = path.substring(0, path.lastIndexOf("\\"));
        final Predicate<Book> beforeDatePredicate = x -> x.getRelease() < year;
        final Predicate<Book> afterDatePredicate = x -> x.getRelease() >= year;

        csvWriter.write(Path.of(pathToFolder + FILENAME_OLD_BOOK), HEADERS, divideAndSort(bookList, beforeDatePredicate));
        csvWriter.write(Path.of(pathToFolder + FILENAME_NEW_BOOK), HEADERS, divideAndSort(bookList, afterDatePredicate));
    }

    private List<Book> divideAndSort(final BookList bookList, final Predicate<Book> predicate) {
        if (!bookList.getBooks().isEmpty()) {
            return bookList.getBooks().stream().filter(predicate).sorted(Comparator.comparing(Book::getRelease)).toList();
        } else {
            return Collections.emptyList();
        }
    }
}
