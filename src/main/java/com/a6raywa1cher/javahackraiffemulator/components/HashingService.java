package com.a6raywa1cher.javahackraiffemulator.components;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class HashingService {
	public String hash(String s) {
		return BCrypt.hashpw(s, BCrypt.gensalt());
	}

	public boolean compare(String raw, String encoded) {
		return BCrypt.checkpw(raw, encoded);
	}
}
