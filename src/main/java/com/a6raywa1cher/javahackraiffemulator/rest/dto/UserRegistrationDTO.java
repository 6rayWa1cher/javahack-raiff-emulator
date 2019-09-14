package com.a6raywa1cher.javahackraiffemulator.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDTO {
	@NotBlank
	@Size(max = 30)
	private String login;

	@NotBlank
	@Size(max = 255)
	private String password;
}
