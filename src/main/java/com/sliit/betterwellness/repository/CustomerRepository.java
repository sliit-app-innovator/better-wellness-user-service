package com.sliit.betterwellness.repository;

import com.sliit.betterwellness.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Transactional
	@Query(value ="SELECT * FROM customer WHERE  LOWER(username) = LOWER(:username)", nativeQuery = true)
	Customer byUserName(String username);

	@Transactional
	@Query(value ="SELECT * FROM customer WHERE username like %:search% OR first_name like %:search% OR last_name like %:search% ", nativeQuery = true)
	List<Customer> search(String search);
}
