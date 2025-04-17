package com.mahendra.library;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.mahendra.library.dao.BookDAO;
import com.mahendra.library.models.Book;

@SpringBootTest
@ContextConfiguration(classes = LibraryApiApplication.class)
public class BookDAOIntegrationTest {

    @Autowired private BookDAO bookDAO;   // NOT A MOCK OBJECT
    
    @Test()
    @Disabled /// Earlier @Ignored
    void testFindBookById() {
        Book book = bookDAO.findById(1).get();
        System.out.println(book.getTitle() + " by " + book.getAuthor());
        assertEquals("Let Us C", book.getTitle());
        assertEquals("Yashwant Kanetkar", book.getAuthor());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/books.csv", numLinesToSkip = 1)
    //@MethodSource("com.mahendra.library.BookDAOIntegrationTest#bookData")
    void testFindBookById(int id, String title, String author) {
        Book book = bookDAO.findById(id).get();
        System.out.println(book.getTitle() + " by " + book.getAuthor());
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
    }

    private List<Book> bookData() {
        return List.of(
            new Book("Let Us C", "Yashwant Kanetkar", "Programming", 'A'),
            new Book("C++", "Bjarne Stroustrup", "Programming", 'A'),
            new Book("Java", "James Gosling", "Programming", 'A')
        );

    }
}
