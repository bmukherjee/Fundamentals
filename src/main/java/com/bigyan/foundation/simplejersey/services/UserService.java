package com.bigyan.foundation.simplejersey.services;

import javax.ws.rs.Path;
import com.bigyan.foundation.simplejersey.entities.User;
import com.google.inject.servlet.RequestScoped;

@RequestScoped @Path("/users")
public class UserService extends EntityService<User>{
	
	public UserService() {
		super(User.class);
	}
	
}
