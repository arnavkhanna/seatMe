package com.reservation.form;

import com.reservation.entity.CustomerNotes;

public class CustomerNotesForm {
    private Integer id;
    private Integer customer_id;
    private String notes;

    public CustomerNotesForm(CustomerNotes customerNotes) {
        this.id = customerNotes.getId();
        this.customer_id = customerNotes.getCustomer_id();
        this.notes = customerNotes.getNotes();
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

    @Override
    public String toString() {
        return "CustomerNotesForm{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", notes='" + notes + '\'' +
                '}';
    }

    public CustomerNotesForm() {
    }


}
