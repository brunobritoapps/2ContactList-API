package com.test.springrestapi.onetomany.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.springrestapi.onetomany.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}