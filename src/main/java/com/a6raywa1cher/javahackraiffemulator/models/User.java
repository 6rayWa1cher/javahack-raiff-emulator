package com.a6raywa1cher.javahackraiffemulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false, unique = true)
	private String login;

	@Column
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonBackReference
	private List<Account> accounts;
}
