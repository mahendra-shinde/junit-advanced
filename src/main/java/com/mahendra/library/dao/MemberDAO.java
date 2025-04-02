package com.mahendra.library.dao;

import java.util.List;
import com.mahendra.library.models.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberDAO extends CrudRepository<Member, Integer> {
	
	List<Member> findByFirstName(String firstName);
	List<Member> findByLastName(String lastName);
	List<Member> findByStatus(char status);
	
}
