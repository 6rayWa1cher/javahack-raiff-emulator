package com.a6raywa1cher.javahackraiffemulator.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Account {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private BigDecimal money;

	@Column
	private Boolean frozenBySafeTransfer;

	@ManyToOne
	@JsonManagedReference
	private User user;
}
