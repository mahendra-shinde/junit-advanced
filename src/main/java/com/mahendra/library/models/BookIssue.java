package com.mahendra.library.models;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="book_issues")
public class BookIssue implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="issue_id",scale = 5, precision = 0)
	private Integer id;

	@Column(name="book_id",scale = 5, precision = 0)
	private Integer bookId;

	@Column(name="member_id",scale = 5, precision = 0)
	private Integer memberId;

	@Temporal(TemporalType.DATE)
	@Column(name="issue_date")
	private Date issueDate;

	@Column(name="return_date_exp")
	@Temporal(TemporalType.DATE)
	private Date estimatedReturnDate;
	
	@Column(name="return_date_actual")
	@Temporal(TemporalType.DATE)
	
	private Date actualReturnDate;
	
	@Column(name="issue_status",length = 1)
	private char status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getEstimatedReturnDate() {
		return estimatedReturnDate;
	}
	public void setEstimatedReturnDate(Date estimatedReturnDate) {
		this.estimatedReturnDate = estimatedReturnDate;
	}
	public Date getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(Date actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public BookIssue(Integer bookId, Integer memberId, Date issueDate, Date estimatedReturnDate,
			Date actualReturnDate, char status) {
		super();
		this.bookId = bookId;
		this.memberId = memberId;
		this.issueDate = issueDate;
		this.estimatedReturnDate = estimatedReturnDate;
		this.actualReturnDate = actualReturnDate;
		this.status = status;
	}
	public BookIssue() {
		super();
	}
	
}
