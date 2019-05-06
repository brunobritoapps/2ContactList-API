package com.test.springrestapi.onetomany.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.springrestapi.onetomany.exception.NotFoundException;
import com.test.springrestapi.onetomany.jpa.ContactRepository;
import com.test.springrestapi.onetomany.model.Contact;


@RestController
@RequestMapping("/api")
public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;
	
    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
    	return contactRepository.findAll();
    }
    
    @GetMapping("/contacts/{id}")
    public Contact getContactByID(@PathVariable Long id) {
    	Optional<Contact> optContact = contactRepository.findById(id);
    	if(optContact.isPresent()) {
    		return optContact.get();
    	}else {
    		throw new NotFoundException("Contact not found with id " + id);
    	}
    }
    
    @PostMapping("/contacts")
    public Contact createContact(@Valid @RequestBody Contact contact) {
        return contactRepository.save(contact);
    }
    
    @PutMapping("/contacts/{id}")
    public Contact updateContact(@PathVariable Long id,
                                   @Valid @RequestBody Contact contactUpdated) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setName(contactUpdated.getName());
                    contact.setLastName(contactUpdated.getLastName());
                    return contactRepository.save(contact);
                }).orElseThrow(() -> new NotFoundException("Contact not found with id " + id));
    }
    
    @DeleteMapping("/contacts/{id}")
    public String deleteContact(@PathVariable Long id) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contactRepository.delete(contact);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("Contact not found with id " + id));
    }
}