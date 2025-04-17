package com.mahendra.library.rest;

import java.util.*;
import com.mahendra.library.dao.MemberDAO;
import com.mahendra.library.exceptions.MemberNotFoundException;
import com.mahendra.library.models.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/members")
public class MemberResource {

	@Autowired private MemberDAO dao;

	@GetMapping(produces="application/json")	
	public ResponseEntity<List<Member>> findAll(){
		List<Member> members = new LinkedList<>();
		dao.findAll().forEach(m -> members.add(m));
		if(members.isEmpty()){
			throw new MemberNotFoundException();
		}
		return new ResponseEntity<>(members,HttpStatus.FOUND);
	}

	@GetMapping(produces="application/json",value="/{ID}")
	public ResponseEntity<Member> findMember(@PathVariable("ID") int memberId)
	{
		Optional<Member> member = dao.findById(memberId);
		if(member.isEmpty()){
			throw new MemberNotFoundException();
		}
		return new ResponseEntity<>(member.get(),HttpStatus.FOUND);
	}

	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Member> save(Member member){
		Member newMember = dao.save(member);
		return new ResponseEntity<>(newMember, HttpStatus.CREATED);
	}

	@PutMapping(produces="application/json", consumes="application/json", value="/{ID}")
	public ResponseEntity<Member> update(@PathVariable("ID") int memberId, Member member){
		Optional<Member> oldMember = dao.findById(memberId);
		if(oldMember.isEmpty()){
			throw new MemberNotFoundException();
		}
		member.setId(memberId);
		Member newMember = dao.save(member);
		return new ResponseEntity<>(newMember,HttpStatus.OK);
	}

	@DeleteMapping(produces="application/json", value="{ID}")
	public ResponseEntity<String> delete(@PathVariable("ID") int memberId){
		dao.deleteById(memberId);
		return new ResponseEntity<>("Member successfully deleted !",HttpStatus.OK);
	}

}
