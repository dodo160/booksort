package com.booksort;

import com.booksort.service.BookSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksortApplication implements CommandLineRunner {

	@Autowired
	private BookSortService bookSortService;

	public static void main(String[] args) {
		SpringApplication.run(BooksortApplication.class, args);
	}

	@Override
	public void run(String... args) {
		bookSortService.sort(args[0], Integer.parseInt(args[1]));
	}
}
