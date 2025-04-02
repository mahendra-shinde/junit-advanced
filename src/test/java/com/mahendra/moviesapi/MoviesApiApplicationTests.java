package com.mahendra.moviesapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.mahendra.library.LibraryApiApplication;


@SpringBootTest
@ContextConfiguration(classes = LibraryApiApplication.class)
class MoviesApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
