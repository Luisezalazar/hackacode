package com.hackacode1;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;

public class JwtSecretMaker {

	@Test
	public void generateSecretKey() {
		SecretKey key = Jwts.SIG.HS512.key().build();
		String encodedKey= DatatypeConverter.printHexBinary(key.getEncoded());
		System.out.printf("Key = [%s])",encodedKey);
	}
}
