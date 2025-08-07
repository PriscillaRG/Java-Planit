package com.descodeuses.planit.entity;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="Project")

public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;


    @Column(nullable = false)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id= id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // relation entre tables
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true) 
    // mappedBy doit correspondre exactement au nom de l'attribut 
    // cascaseType.ALL : toutes les opérations JPA(save, update, delete, etc) effectuées sur un ProjectEntity seront répercutées sur ses actions (ActionEntity)
    // orphanRemoval = true : si une ActionEntity est retirée de la liste; automatiquement supprimée de la base de données
    private List<ActionEntity> actions = new ArrayList<>();

    public List<ActionEntity> getActions() {
        return actions;
    }

    public void setActions(List<ActionEntity> actions) {
        this.actions = actions;
    }
}
