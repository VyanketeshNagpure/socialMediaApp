package com.socially.backend.dto;

import jakarta.validation.constraints.NotEmpty;

public class SignUpDto {
	
	@NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String userName;

    @NotEmpty
    private char[] password;

    public SignUpDto() {
        super();
    }

    public SignUpDto(@NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String userName, @NotEmpty char[] password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
