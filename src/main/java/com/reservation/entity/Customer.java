package com.reservation.entity;

import com.reservation.form.CustomerForm;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String phonenumber;
    private Integer partysize;
    private Integer numKids;
    private String status;
    LocalDateTime createtime;
    LocalDateTime updatetime;
    LocalDateTime reservationtime;


    public Customer() {
    }

    public Customer(CustomerForm customerForm) {
        this.id = customerForm.getId();
        this.name = customerForm.getName();
        this.phonenumber = customerForm.getPhonenumber();
        this.partysize = customerForm.getPartysize();
        this.numKids = customerForm.getNumKids();
        this.createtime = LocalDateTime.now();
        this.updatetime = LocalDateTime.now();
        this.reservationtime = this.createtime.plusHours(1);
        if (customerForm.getId() == null) {
            this.status = "Waiting";
        } else {
            this.status = customerForm.getStatus();
        }
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

    public LocalDateTime getReservationtime() {
        return reservationtime;
    }

    public void setReservationtime(LocalDateTime reservationtime) {
        this.reservationtime = reservationtime;
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
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", partysize=" + partysize +
                ", numKids=" + numKids +
                ", status='" + status + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", reservationtime=" + reservationtime +
                '}';
    }
}
