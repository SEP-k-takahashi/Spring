package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


	@Repository
	public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM address_sp WHERE derete_flg = 0", nativeQuery = true)
		Page<User> findAllByFreeWord(Pageable pageable);

	@Query(value = "SELECT * FROM address_sp WHERE derete_flg = 0 and address LIKE %:name%  ", nativeQuery = true)
	Page<User> findAllByFreeSearch(@Param("name")String str1,Pageable pageable);

	@Query(value = "select u from User u " )
	Page<User> find(Pageable pageable);

	}
