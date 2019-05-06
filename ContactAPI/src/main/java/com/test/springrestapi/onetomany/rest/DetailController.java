package com.test.springrestapi.onetomany.rest;

import java.util.List;

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
import com.test.springrestapi.onetomany.jpa.DetailRepository;
import com.test.springrestapi.onetomany.jpa.ContactRepository;
import com.test.springrestapi.onetomany.model.Detail;

@RestController
@RequestMapping("/api")
public class DetailController {
	@Autowired
	private DetailRepository detailRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
    @GetMapping("/contacts/{contactId}/details")
    public List<Detail> getContactByContactId(@PathVariable Long contactId) {
    	
        if(!contactRepository.existsById(contactId)) {
            throw new NotFoundException("Contact not found!");
        }
    	
    	return detailRepository.findByContactId(contactId);
    }
    
    @PostMapping("/contacts/{contactId}/details")
    public Detail addDetail(@PathVariable Long contactId,
                            @Valid @RequestBody Detail detail) {
        return contactRepository.findById(contactId)
                .map(contact -> {
                    detail.setContact(contact);
                    return detailRepository.save(detail);
                }).orElseThrow(() -> new NotFoundException("Contact not found!"));
    }
    
    @PutMapping("/contacts/{contactId}/details/{detailId}")
    public Detail updateAssignment(@PathVariable Long contactId,
    								@PathVariable Long detailId,
    								@Valid @RequestBody Detail detailUpdated) {
    	
    	if(!contactRepository.existsById(contactId)) {
    		throw new NotFoundException("Contact not found!");
    	}
    	
        return detailRepository.findById(detailId)
                .map(detail -> {
                    detail.setPhone(detailUpdated.getPhone());
                    detail.setMail(detailUpdated.getMail());
                    return detailRepository.save(detail);
                }).orElseThrow(() -> new NotFoundException("Detail not found!"));
    }
    
    @DeleteMapping("/contacts/{contactId}/details/{detailId}")
    public String deleteAssignment(@PathVariable Long contactId,
    							   @PathVariable Long detailId) {
    	
    	if(!contactRepository.existsById(contactId)) {
    		throw new NotFoundException("Contact not found!");
    	}
    	
        return detailRepository.findById(detailId)
                .map(detail -> {
                    detailRepository.delete(detail);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Contact not found!"));
    }
}
