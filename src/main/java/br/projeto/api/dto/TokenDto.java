package br.projeto.api.dto;

public class TokenDto {

	private String token;
	
	public TokenDto() {
		
	}
	
	
	public TokenDto(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
