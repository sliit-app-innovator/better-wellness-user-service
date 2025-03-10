package com.sliit.betterwellness.repository;

import com.sliit.betterwellness.entity.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CounsellorRepository extends JpaRepository<Counsellor, Integer> {
	@Transactional
	@Query(value ="SELECT * FROM counsellor WHERE  LOWER(username) = LOWER(:username)", nativeQuery = true)
	Counsellor byUserName(String username);

	@Transactional
	@Query(value ="SELECT * FROM counsellor WHERE username like %:search% OR first_name like %:search% OR last_name like %:search% OR specializations like %:search%", nativeQuery = true)
	List<Counsellor> search(String search);
}
