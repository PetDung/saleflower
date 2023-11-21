package com.code.orishop.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdAt;


    @PrePersist
    public void setCreationDate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    public void setChangeDate() {
        this.updatedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
