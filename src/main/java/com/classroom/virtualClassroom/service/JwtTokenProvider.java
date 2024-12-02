package com.classroom.virtualClassroom.service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
	@Value("${jwt.secret}")
	private String SECRET_KEY;
	@Value("${jwt.expiration}")
	  private long expiration;

	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getSubject);
	}
	
	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		    final Claims claims = extractAllClaims(token);
		    return claimsResolver.apply(claims);
		  }
	 
	 public String generateToken(UserDetails userDetails) {
		    return generateToken(new HashMap<>(), userDetails);
		  }
	 
	 public boolean isTokenValid(String token, UserDetails userDetails) {
		    final String username = extractUsername(token);
		    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
		  }
	 
	 private boolean isTokenExpired(String token) {
		    return extractExpiration(token).before(new Date());
		  }
	 
	 private Date extractExpiration(String token) {
		    return extractClaim(token, Claims::getExpiration);
		  }
	 
	 public String generateToken(
		      Map<String, Object> extraClaims,
		      UserDetails userDetails
		  ) {
//		 String roles = userDetails.getAuthorities().stream()
//	                .map(GrantedAuthority::getAuthority)
//	                .collect(Collectors.joining(","));
		 return Jwts
		            .builder()
		            .claims(extraClaims)
		            .subject(userDetails.getUsername())
		            .issuedAt(new Date(System.currentTimeMillis()))
		            .expiration(new Date(System.currentTimeMillis() + expiration))
		            .claim("role", userDetails.getAuthorities())
//		            
//		            .claim("role", roles)
		            .signWith(getSignInKey(),Jwts.SIG.HS256)
		            .compact();
		  }
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
		        .verifyWith(getSignInKey())
		        .build()
		        .parseSignedClaims(token)
		        .getPayload();
	  }
	
	 private SecretKey getSignInKey() {
		    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		    return Keys.hmacShaKeyFor(keyBytes);
		  }
}
