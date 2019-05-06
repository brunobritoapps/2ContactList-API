package com.test.springrestapi.onetomany.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.springrestapi.onetomany.model.Detail;

public interface DetailRepository extends JpaRepository<Detail, Long> {
	List<Detail> findByContactId(Long contactId);	
}
