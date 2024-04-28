package com.snhu.sslserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.bucket4j.Bucket;
import io.jsonwebtoken.Jwts;
import java.security.MessageDigest;
import java.time.Duration;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

@RestController
class ServerController {
	@Autowired
    private Environment env;
    private SecretKey encodedKey;
    private String jwt;
    private String data;
    private ErrorHandler errorHandler;
    private static Bucket bucket;
    
    @PostConstruct
    private void initialize() {
    	// Hard-code a JWT for example
        this.encodedKey = Jwts.SIG.HS256.key().build(); // SHA-256
        this.jwt = Jwts.builder().subject("example").signWith(encodedKey).compact();
        this.data = env.getProperty("server.data");
        this.errorHandler = new ErrorHandler();
        ServerController.bucket = Bucket.builder()
        	      .addLimit(limit -> limit.capacity(20).refillGreedy(10, Duration.ofMinutes(1)))
        	      .build();
    }
	


	@RequestMapping("/hash")
	public String myHash(HttpServletResponse res) {
		/**
		 * Author: Bobby Rust | April 6, 2024
		 * 
		 * This is an API route which contains hard-coded data which is hashed using
		 * SHA-256 and returned as a hexadecimal string.
		 */

		try {
			if (bucket.tryConsume(1)) {
				Jwts.parser().verifyWith(encodedKey).build().parseSignedClaims(jwt); // verify jwt
				
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] byteHash = md.digest(data.getBytes());
				String hexHash = Hex.encodeHexString(byteHash); // uses the Apache Commons Codec Hex class
				
				return "<p>data:" + data + "<br>Name of Algorithm Cipher Used: " + md.getAlgorithm() + "<br>Value: "
				+ hexHash + "</p>";
			} else {
				throw new Exception("Rate limited");
			}
		} catch (Exception e) {
			errorHandler.handleError(e, res);
		}
		return null;
	}
}