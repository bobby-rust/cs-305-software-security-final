package com.snhu.sslserver;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.jsonwebtoken.JwtException;

@Controller
public class ErrorHandler implements ErrorController {
	@RequestMapping("/error")
	public String handleError(Exception e, HttpServletResponse res) {
		if (e instanceof JwtException) { res.setStatus(401); } // unauthorized
		else if (e instanceof NoSuchAlgorithmException) { res.setStatus(500); } // Internal server error
		else if (e.getMessage() == "Rate limited") { res.setStatus(429); } // rate limiting exception
		else { res.setStatus(520); } // unknown
		
		return "error.html";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
