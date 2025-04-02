package com.mahendra.library.dao;

import java.util.Date;
import java.util.List;

import com.mahendra.library.models.BookIssue;

import org.springframework.data.repository.CrudRepository;

public interface BookIssueDAO extends CrudRepository<BookIssue, Integer> {

	List<BookIssue> findByIssueDate(Date issueDate);
	List<BookIssue> findByEstimatedReturnDate(Date estimatedReturnDate);
	List<BookIssue> findByBookId(Integer bookId);
	List<BookIssue> findByMemberId(Integer memberId);
	List<BookIssue> findByStatus(char status);

}
