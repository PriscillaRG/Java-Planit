package com.descodeuses.planit.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name ="last_name")
    private String lastName;
    
    @Column(name ="name")
    private String name;

    public String getName() {
        return name;
    }

 
    public void getLastName(String lastName) {
        this.name = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String email;
    
    public String getEmail() {
        return email;
    }


    @ManyToMany(mappedBy = "members")
    private Set<ActionEntity> todos = new HashSet<>();

    public Set<ActionEntity> getTodos() {
        return todos;
    }

    public void setTodos(Set<ActionEntity> todos) {
        this.todos = todos;
    }

    public void setEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
    }

    public String getLastName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastName'");
    }
}
