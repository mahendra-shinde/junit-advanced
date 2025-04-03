package com.mahendra.library;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.mahendra.library.dao.BookDAO;
import com.mahendra.library.models.Book;


// @SpringBootTest
// @ContextConfiguration(classes = {LibraryApiApplication.class})
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class BookDAOTest {

    //    @Autowired  private BookDAO bookDAO;
    @Mock private BookDAO bookDAO; // Mocking the BookDAO

    Book book;

    @BeforeEach
    public void setUp() {
        // Initialize the mock object before each test
        book = new Book("Sample Title", "Sample Author", "Learning", 'A');   
    }

    @Test
    @Order(1)
    public void testAddBook() {
        when(bookDAO.save(any(Book.class))).thenReturn(book);;

        Book test = bookDAO.save(book); 
        assertNotNull(test,"Returned value is null");
        assertSame(book, test,"Book not created");
    }

    @Test
    @Order(2)
    public void testGetBook() {
        when(bookDAO.findById(1)).thenReturn(java.util.Optional.of(book)); // Mocking the findById method

        Book test = bookDAO.findById(1).orElse(null); // Call the method to be tested
        assertNotNull(test,"Returned value is null");
        assertSame(book, test,"Book not found");
    }
}
