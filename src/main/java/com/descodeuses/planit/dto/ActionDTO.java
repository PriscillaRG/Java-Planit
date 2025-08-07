package com.descodeuses.planit.dto;

import java.time.LocalDate;
import java.util.Set;

public class ActionDTO {

    private Long id;
    private String title;
    private boolean completed;
    private LocalDate dueDate;
    private int priority;
    private Long projectId;
    private Long userConnectedId;
    private Set<Long> memberIds;

    public ActionDTO(Long id, String title, boolean completed, LocalDate dueDate, Integer priority) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.dueDate = dueDate;
        this.priority = 0; // Default priority
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

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setMemberIds(Set<Long> memberIds) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'setMemberIds'");
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserConnectedId() {
        return userConnectedId;
    }

    public void setUserConnectedId(Long userConnectedId) {
        this.userConnectedId = userConnectedId;
    }
    public Set<Long> getMemberIds() {
        return memberIds;
    }

}
