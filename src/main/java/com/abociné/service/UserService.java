package com.abociné.service;

import java.util.List;

import com.abociné.dao.User;



  interface  UserService {
	
	  User addUser(User user) ;
	  List<User> findAllUser() ; 
	  User getUserById(int id) ; 
	  void deleteUser(int id) ; 
	  void updateUser(int id , User user) ; 
	  List<User> searchUser(String input) ; 
}
