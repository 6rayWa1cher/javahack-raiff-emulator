package com.a6raywa1cher.javahackraiffemulator.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransferMoneyDTO {
	@NotNull
	private Integer fromId;
	@NotNull
	private Integer toId;
	@NotNull
	private BigDecimal amount;
}
