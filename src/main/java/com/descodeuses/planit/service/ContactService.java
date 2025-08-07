package com.descodeuses.planit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.descodeuses.planit.dto.ContactDTO;
import com.descodeuses.planit.entity.ContactEntity;
import com.descodeuses.planit.repository.ContactRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContactService {
    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    private ContactDTO convertToDTO(ContactEntity contact) {
        return new ContactDTO(
        contact.getId(),
        contact.getLastName(),
        contact.getName(),
        contact.getEmail());
    }

    private ContactEntity convertToEntity(ContactDTO contactDTO){
        ContactEntity contact = new ContactEntity();
        contact.setId(contactDTO.getId());
        contact.getLastName(contactDTO.getLastName());
        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());

        return contact;
    }
//DTO list
    public List<ContactDTO> getContactDTOs() {
        List<ContactEntity> contactsList = repository.findAll();
        List<ContactDTO> contactsDtosList = new ArrayList<>();
        for(ContactEntity item: contactsList) {
            contactsDtosList.add(convertToDTO(item));           
        }
        return contactsDtosList ;
    } 
//DTO contact by id
    public ContactDTO getContactById(Long id) {
        Optional<ContactEntity> contactById= repository.findById(id);
        if(contactById.isEmpty()){
            throw new EntityNotFoundException("Contact not found with id: "+id);
        }
        return convertToDTO(contactById.get());
    }

// update contact
    public ContactDTO update(Long id, ContactDTO dto) {
        Optional<ContactEntity> existContact = repository.findById(id);
        if (existContact.isEmpty()) {
            throw new EntityNotFoundException("Contact not found with id :" +id);
        }   
        ContactEntity contact = existContact.get();
        contact.getLastName(dto.getLastName());
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());

        ContactEntity updatedContact = repository.save(contact);
        return convertToDTO((updatedContact));
     }
//delete contact
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Contact not found with id :" +id);
        }
        repository.deleteById(id);
    }

// create contact
public ContactDTO create(ContactDTO dto) {
    ContactEntity entity = convertToEntity(dto);
    ContactEntity savedEntity = repository.save(entity);
    return convertToDTO(savedEntity);
}

}
