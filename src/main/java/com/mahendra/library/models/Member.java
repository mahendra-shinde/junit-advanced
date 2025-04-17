package com.mahendra.library.models;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="members")
public class Member implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_id",scale = 5, precision = 0)
	private Integer id;

	@Column(name="first_name",length = 30)
	private String firstName;

	@Column(name="last_name",length = 30)
	private String lastName;

	@Column(name="member_status",length = 1)
	private char status;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Member( String firstName, String lastName, char status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
	}
	public Member() {
		super();
	}
	
}
