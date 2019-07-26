package com.reservation.form;

import com.reservation.entity.Customer;

public class CustomerNotesForm {
    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "CustomerNotesForm{" +
                "notes='" + notes + '\'' +
                '}';
    }

    public CustomerNotesForm() {
    }

    public CustomerNotesForm(Customer customer) {
        this.notes = customer.getNotes();
    }
}
