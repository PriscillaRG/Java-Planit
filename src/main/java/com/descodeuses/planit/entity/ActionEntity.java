package com.descodeuses.planit.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="todo")
public class ActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

      public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    // 🔗 Nouvelle relation avec un projet
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column(nullable = false)
    private String title;

    private boolean completed;

    private LocalDate dueDate;

    @Column(nullable = true)
    private Integer priority;

    //private UserEntity userConnected;

    @ManyToMany
    @JoinTable(
        name = "todo_contact",
        joinColumns = @JoinColumn(name = "todo_id"),
        inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Set<ContactEntity> members = new HashSet<>();

    public Set<ContactEntity> getMembers() {
        return members;
    }

    public void setMembers(Set<ContactEntity> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

     public Integer getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

/*     public void setUserConnected(UserEntity user) {
        this.userConnected = user;
    } */

    public ProjectEntity getProject() {
    return project;
}
    
    public void setProject(ProjectEntity project) {
    this.project = project;
}

/*     public UserEntity getUserConnected() {
        return userConnected;
    }
    public void setUserConnectedId(UserEntity userConnected) {
        this.userConnected = userConnected;} */

}
