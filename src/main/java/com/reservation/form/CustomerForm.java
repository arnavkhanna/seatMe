package com.reservation.form;

import com.reservation.entity.Customer;
import com.reservation.form.CustomerNotesForm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerForm {
    private Integer id;
    private String name;
    private String phonenumber;
    private Integer partysize;
    private Integer numKids;
    private String status;
    private String notes;


    public CustomerForm() {
    }

    public CustomerForm(Customer customer, CustomerNotesForm customerNotesForm) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.phonenumber = customer.getPhonenumber();
        this.partysize = customer.getPartysize();
        this.numKids = customer.getNumKids();
        this.status = customer.getStatus();

        if (customerNotesForm != null) {
            this.notes = customerNotesForm.getNotes();
        }

    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getPartysize() {
        return partysize;
    }

    public void setPartysize(Integer partysize) {
        this.partysize = partysize;
    }

    public Integer getNumKids() {
        return numKids;
    }

    public void setNumKids(Integer numKids) {
        this.numKids = numKids;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomerForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", partysize=" + partysize +
                ", numKids=" + numKids +
                ", status='" + status + '\'' +
                '}';
    }
}
