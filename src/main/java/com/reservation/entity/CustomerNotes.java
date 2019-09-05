package com.reservation.entity;

import com.reservation.form.CustomerNotesForm;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "notes")
public class CustomerNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    private String notes;
    LocalDateTime createtime;
    LocalDateTime updatetime;

    public CustomerNotes(CustomerNotesForm customerNotesForm){
        this.id = customerNotesForm.getId();
        this.customerId = customerNotesForm.getCustomerId();
        this.notes = customerNotesForm.getNotes();
        this.createtime = LocalDateTime.now();
        this.updatetime = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

}
