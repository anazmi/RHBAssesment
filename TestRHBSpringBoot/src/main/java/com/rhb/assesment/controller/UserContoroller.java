package com.rhb.assesment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhb.assesment.model.User;
import com.rhb.assesment.repo.CrudRepo;

@RestController
public class UserContoroller {
	
	@Autowired
	CrudRepo repo;
	
	@PostMapping(path="/persons",consumes= {"application/json"})
	public User addUser(@RequestBody User user)
	{
		repo.save(user);
		return user;
	}
	
	@GetMapping(path="/persons")
	public List<User> getUsers()
	{
		System.out.println("test getUsers");
		return repo.findAll();
			
	}
	
	@GetMapping("/persons/getPersonByFirstName/{firstname}")
	public List<User> getUsers(@PathVariable("firstname")String firstname)
	{
		return repo.findByFirstName(firstname);	
	}
	
	
	/*
	 * @PutMapping(path="/persons/{firstname}",consumes= {"application/json"})
	 * public List<User> saveOrUpdateUser(@PathVariable("firstname")String
	 * firstname, @RequestBody User user) { try { if(null!=firstname) {
	 * 
	 * List<User> user1 = repo.findByFirstName(firstname);
	 * 
	 * if(user1.size() > 0) { System.out.println("user1.size() " + user1.size());
	 * System.out.println("user " + user.getEmail()); for(User u : user1) {
	 * repo.setUserInfoById(u.getFirstName(), u.getLastName(), user.getEmail(),
	 * u.getUserid()); } }
	 * 
	 * } }catch(Exception e) { e.printStackTrace(); }
	 * 
	 * return repo.findByFirstName(firstname); }
	 */
	
	@PutMapping(path="/persons/{firstname}",consumes= {"application/json"})
	public List<User> UpdateEmailUser(@PathVariable("firstname")String firstname, @RequestBody User user)
	{
		try {
			
			repo.setUserInfoByFirstName(user.getEmail(), firstname);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return repo.findByFirstName(firstname);
	}
	
	@DeleteMapping("/persons/{firstname}")
	public List<User> deleteUser(@PathVariable("firstname")String firstname)
	{
		System.out.println("Delete firstname " + firstname);
		repo.deleteByFirstName(firstname);
		return repo.findAll();
	}
	

}
