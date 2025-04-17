package com.mahendra.library.rest;

import com.mahendra.library.dao.BookIssueDAO;
import com.mahendra.library.exceptions.IssueNotFoundException;
import com.mahendra.library.models.BookIssue;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
public class BookIssueResource {

	@Autowired private BookIssueDAO dao;
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<BookIssue>> findAll(){
		List<BookIssue> issues = new LinkedList<>();
		dao.findAll().forEach(issues::add);
		if(issues.isEmpty()){
			throw new IssueNotFoundException();
		}
		return new ResponseEntity<>(issues,HttpStatus.OK);
	}

	@GetMapping(produces="application/json",value="/{ID}")
	public ResponseEntity<BookIssue> findIssue(@PathVariable("ID") int issueId){
		Optional<BookIssue> issue = dao.findById(issueId);	
		if(issue.isEmpty()){
			throw new IssueNotFoundException();
		}
		return new ResponseEntity<>(issue.get(), HttpStatus.OK);
	}

	@PostMapping(consumes="application/json",produces="application/json")
	public ResponseEntity<BookIssue> save(BookIssue issue){
		BookIssue newIssue = dao.save(issue);
		return new ResponseEntity<>(newIssue,HttpStatus.CREATED);
	}

	@PutMapping(value="{ID}", produces="application/json", consumes="application/json")
	public ResponseEntity<BookIssue> update(@PathVariable("ID") int issueId, BookIssue issue ){
		Optional<BookIssue> oldIssue = dao.findById(issueId);	
		if(oldIssue.isEmpty()){
			throw new IssueNotFoundException();
		}
		issue.setId(issueId);
		BookIssue newIssue = dao.save(issue);
		return new ResponseEntity<>(newIssue,HttpStatus.OK);
	}

	@DeleteMapping(value="{ID}", produces="application/json")
	public ResponseEntity<String> delete (@PathVariable("ID") int issueId){
		dao.deleteById(issueId);
		return new ResponseEntity<>("Member successfully deleted !",HttpStatus.OK);
	}
}
