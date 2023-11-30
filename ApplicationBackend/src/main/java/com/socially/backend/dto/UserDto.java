package com.socially.backend.dto;

import java.util.List;

public class UserDto {
	
	 private Long id;
	    private String firstName;
	    private String lastName;
	    private String userName;
	    private String sociallyBio;
	    private List<String> following;
	    private List<String> followers;
	    private String token;

	    public UserDto() {
	        super();
	    }
	    

	    public UserDto(Long id, String firstName, String lastName, String userName, String sociallyBio) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.userName = userName;
			this.sociallyBio = sociallyBio;
		}



		public UserDto(Long id, String firstName, String lastName, String userName, String sociallyBio,
				List<String> following, List<String> followers, String token) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.userName = userName;
			this.sociallyBio = sociallyBio;
			this.following = following;
			this.followers = followers;
			this.token = token;
		}

		public List<String> getFollowing() {
			return following;
		}


		public void setFollowing(List<String> following) {
			this.following = following;
		}


		public List<String> getFollowers() {
			return followers;
		}


		public void setFollowers(List<String> followers) {
			this.followers = followers;
		}


		public String getSociallyBio() {
			return sociallyBio;
		}

		public void setSociallyBio(String sociallyBio) {
			this.sociallyBio = sociallyBio;
		}

		public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
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

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

}
