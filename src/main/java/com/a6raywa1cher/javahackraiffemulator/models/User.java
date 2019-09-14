package com.a6raywa1cher.javahackraiffemulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonBackReference
	private List<Account> accounts;

	@OneToOne
	private Account favoriteAccount;
}
