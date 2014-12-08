package com.abociné.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.abociné.dao.User;
import com.abociné.service.UserServiceImpl;

@Path("/users")
public class UserServiceRest {

	UserServiceImpl userService = new UserServiceImpl();

	

	@POST
	@Path("/add")
	public void addUser(User user) {
		userService.addUser(user);
	}

	@Produces("application/json")
	@Path("projects")
	@GET
	public List<User> getAllUsers() {

		return userService.findAllUser();
	}

	@Produces("application/json")
	@Path("projects/{id}")
	@GET
	public User getUserById(@PathParam("id") int id) {
		return userService.getUserById(id);

	}

	@Path("/delete/{id}")
	@DELETE
	public void deleteUser(@PathParam("id") int id) {
		userService.deleteUser(id);
	}

	@Path("/update/{id}")
	@PUT
	public void updateUser(@PathParam("id") int id, User user) {

		User utilisateur = userService.getUserById(id);
		utilisateur.setNom(user.getNom());
		utilisateur.setPrenom(user.getPrenom());
		utilisateur.setEmail(user.getEmail());

		userService.updateUser(id, user);
	}

	@Produces("application/json")
	@Path("/search/{input}")
	@GET
	public List<User> searchUser(@PathParam("input") String input) {
		return userService.searchUser(input);

	}

}