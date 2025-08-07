package com.descodeuses.planit.dto;

import java.time.LocalDate;

public class ProjectDto {
 private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public ProjectDto(Long id, String title, LocalDate startDate, LocalDate endDate, String status)
    {
        this.id= id;
        this.title= title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status= status;
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
}
