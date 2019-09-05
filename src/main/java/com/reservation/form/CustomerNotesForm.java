package com.reservation.form;

import com.reservation.entity.CustomerNotes;

public class CustomerNotesForm {
    private Integer id;
    private Integer customerId;
    private String notes;

    public CustomerNotesForm(CustomerNotes customerNotes) {
        this.id = customerNotes.getId();
        this.customerId = customerNotes.getCustomerId();
        this.notes = customerNotes.getNotes();
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

    @Override
    public String toString() {
        return "CustomerNotesForm{" +
                "id=" + id +
                ", customer_id=" + customerId +
                ", notes='" + notes + '\'' +
                '}';
    }

    public CustomerNotesForm() {
    }


}
