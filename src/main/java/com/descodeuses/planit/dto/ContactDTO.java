package com.descodeuses.planit.dto;

public class ContactDTO {
    //id 
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //lastname
    private String lastName;
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //firstname
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Contact DTO
    public ContactDTO(Long id, String lastName, String name, String email){
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.email = email;
    }

 

}
