package com.rhb.assesment.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rhb.assesment.model.User;

public interface CrudRepo extends JpaRepository<User,Integer>{
	
	List<User> findByFirstName(String firstName);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM USER WHERE firstname=?1", nativeQuery = true)	 
	void deleteByFirstName(String firstName);
	
	@Transactional
	@Modifying
	@Query("update User set firstname = ?1, lastname = ?2, email=?3 where userid = ?4")
	void setUserInfoById(String firstname, String lastname, String email, Integer userId);
	
	@Transactional
	@Modifying
	@Query("update User set email=?1 where firstname = ?2")
	void setUserInfoByFirstName(String email, String firstname);

}
