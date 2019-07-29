package com.reservation.entity;

import com.reservation.form.CustomerNotesForm;

import java.time.LocalDateTime;

public class CustomerNotes {
    private Integer id;
    private Integer customer_id;
    private String notes;
    LocalDateTime createtime;
    LocalDateTime updatetime;

    public CustomerNotes(CustomerNotesForm customerNotesForm){
        this.id = customerNotesForm.getId();
        this.customer_id = customerNotesForm.getCustomer_id();
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

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
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
