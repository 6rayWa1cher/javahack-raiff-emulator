package com.a6raywa1cher.javahackraiffemulator.rest.mirror;

import com.a6raywa1cher.javahackraiffemulator.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserMirror {
	private Integer id;

	private String login;

	private List<AccountMirror> accounts;

	private AccountMirror favoriteAccount;

	public UserMirror(Integer id) {
		this.id = id;
	}

	public static UserMirror convert(User user) {
		UserMirror mirror = new UserMirror();
		mirror.setId(user.getId());
		mirror.setLogin(user.getLogin());
		mirror.setFavoriteAccount(AccountMirror.convert(user.getFavoriteAccount()));
		return mirror;
	}
}
