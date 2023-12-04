package com.socially.backend.dto;

public class CredentialsDto {
	
	private String userName;
    private char[] password;

    public CredentialsDto() {
        super();
    }

    public CredentialsDto(String userName, char[] password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

}
