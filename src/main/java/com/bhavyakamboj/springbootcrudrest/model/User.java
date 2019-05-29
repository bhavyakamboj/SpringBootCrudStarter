package com.bhavyakamboj.springbootcrudrest.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/*
* fields should automatically get populated whenever we create or update an entity.
To achieve this, we need to do two things -
1. Add Spring Data JPA’s AuditingEntityListener to the domain model.
2. Enable JPA Auditing in the main application.

-- @EntityListeners(AuditingEntityListener.class) in User class
-- @EnableJpaAuditing in SpringBootCrudRestApplication.java
* */
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

/*
@CreatedDate - Declares a field as the one representing the date the entity containing the field was created.
@LastModifiedDate - Declares a field as the one representing the date the entity containing the field was recently modified.
@CreatedBy- Declares a field as the one representing the principal that created the entity containing the field.
@LastModifiedBy - Declares a field as the one representing the principal that recently modified the entity containing the field.
 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email_address", nullable = false)
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column(name = "updated_by", nullable = false)
    @LastModifiedBy
    public String getUpdatedBy() {
        return updatedBy;
    }
        public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}