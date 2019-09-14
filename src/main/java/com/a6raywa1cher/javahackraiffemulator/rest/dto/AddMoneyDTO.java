package com.a6raywa1cher.javahackraiffemulator.rest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddMoneyDTO {
	private BigDecimal money;
	private Boolean safeTransfer;
}
