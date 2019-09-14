package com.a6raywa1cher.javahackraiffemulator.rest.mirror;

import com.a6raywa1cher.javahackraiffemulator.models.Account;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountMirror {
	private Integer id;

	private BigDecimal money;

	private Boolean frozenBySafeTransfer;

	private UserMirror user;

	public static AccountMirror convert(Account account) {
		AccountMirror mirror = new AccountMirror();
		mirror.setId(account.getId());
		mirror.setMoney(account.getMoney());
		mirror.setFrozenBySafeTransfer(account.getFrozenBySafeTransfer());
		mirror.setUser(new UserMirror(account.getUser().getId()));
		return mirror;
	}
}
