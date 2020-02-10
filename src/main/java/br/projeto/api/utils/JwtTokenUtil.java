package br.projeto.api.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	static final String CLAIM_KEY_USERNAME = "sbu";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_CREATED= "created";
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	//obtem o username (email) contido no token
	public String getUsernameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = (String) claims.get("sbu");
		}catch(Exception e) {
			username= null;
		}
		
		return username;
	}
	
	//retorna a data de expiração
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		}catch(Exception e) {
			expiration= null;
		}
		
		return expiration;
	}
	
	//refresh para o token 
	
	public String refreshToken(String token) {
		String refreshedToken;
		
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = gerarToken(claims);
		}catch(Exception e) {
			refreshedToken= null;
		}
		
		return refreshedToken;
	}
	
	//verifica se o token é valido
	
	public boolean tokenValido(String token) {
		return !tokenExpirado(token);
	}
	
	//retorna o novo token
	public String obterToken(UserDetails userDetails) {
		Map<String,Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		userDetails.getAuthorities().forEach(authority->claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		claims.put(CLAIM_KEY_CREATED, new Date());
		
		return gerarToken(claims);
	}
	
	//realiza o parse do token para extrair as informações contidas no corpo
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			claims = null;
		}
		
		return claims;
	}
	
	//retorna a data de expiração com base na data atual
	
	private Date gerarDataExpiracao() {
		return new Date(System.currentTimeMillis()+expiration*1000);
	}
	
	//verifica se o token esta expirado
	private boolean tokenExpirado(String token) {
		Date dataExpiracao = this.getExpirationDateFromToken(token);
		if(dataExpiracao==null) {
			return false;
		}
		
		return dataExpiracao.before(new Date());
	}
	
	//Gera um novo token
	private String gerarToken(Map<String,Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(gerarDataExpiracao()).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
}
