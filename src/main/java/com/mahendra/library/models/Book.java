package com.mahendra.library.models;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Book implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id",scale = 5, precision = 0)
	private Integer id;

	@Column(name = "title", length = 50)
	private String title;

	@Column(name="book_author",length = 50)
	private String author;

	@Column(name="book_category",length = 40)
	private String category;

	@Column(name="book_status",length = 1)
	private char status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Book(String title, String author, String category, char status) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
	}
	public Book() {
		super();
	}
}
