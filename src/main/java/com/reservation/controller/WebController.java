package com.reservation.controller;

import com.reservation.entity.Customer;
import com.reservation.form.CustomerForm;
import com.reservation.form.CustomerNotesForm;
import com.reservation.services.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class WebController {

    private final Logger logger = LogManager.getLogger(getClass());
    private final ReservationService reservationService;

    public WebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String index(Model model) {

        logger.info("request to index page");

        model.addAttribute("carform", new CustomerForm());
        return "getPage";
    }

    @PostMapping(value = "/")
    public String post(@ModelAttribute CustomerForm customerForm, Model model){
        logger.info(customerForm);
        Optional<Customer> customerVar = reservationService.locateCustomer(customerForm.getId());
        if (customerVar.isPresent()) {
            Customer customer = customerVar.get();
            model.addAttribute("car", customer);
            return "getPageResults";
        }
        else{
            model.addAttribute("carform", new CustomerForm());
            model.addAttribute("Error", "ID not found");
            return "getPage";
        }




    }

    @GetMapping(value = "/add")
    public String add(@ModelAttribute CustomerForm customerForm, Model model){
        logger.info(customerForm);
        model.addAttribute("buttonVal", "Add");
        model.addAttribute("customerform", new CustomerForm());
        model.addAttribute("Action","add");
        return "addPage";

    }

    @PostMapping(value = "/add")
    public String addResults(@ModelAttribute CustomerForm customerForm, Model model){
        model.addAttribute("Action","add");
        logger.info(customerForm);
        Customer customer = reservationService.saveCustomer(customerForm);
        model.addAttribute("buttonVal", "Add");
        if (customer == null){
            model.addAttribute("customerform", customerForm);
            model.addAttribute("messages", "There was an error adding the values to the database.");
            return "addPage";
        }
        else{
            model.addAttribute("customerform",new CustomerForm());
            model.addAttribute("messages", "Successfully added.");
            return "addPage";
        }
    }

    @GetMapping(value = "/all")
    public String listAll(Model model){
        ArrayList<Customer> customerList = reservationService.showAll();
        model.addAttribute("customers", customerList);
        return "listPage";
    }
    @GetMapping(value = "/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        model.addAttribute("Action","/edit/" + id);
        Optional<Customer> customerVar = reservationService.locateCustomer(id);
        if (customerVar.isPresent()){
            CustomerForm customerForm = new CustomerForm(customerVar.get(), null);
            model.addAttribute("customerform", customerForm);
        }
        else{
            model.addAttribute("messages","There was an error updating values");
        }

        model.addAttribute("buttonVal", "Update");
        return "editPage";
    }
    @PostMapping(value = "/edit/{id}")
    public String editPost(Model model, CustomerForm customerForm, @PathVariable int id){
        model.addAttribute("Action","/edit/" + id);
        model.addAttribute("messages","Successfully updated.");
        model.addAttribute("buttonVal", "Update");
        model.addAttribute("customerform", customerForm);
        reservationService.saveCustomer(customerForm);
        return "editPage";
    }

    @GetMapping(value = "/remove/{id}")
    public String delete(Model model, @PathVariable int id){
        Optional<Customer> carVar = reservationService.locateCustomer(id);
        if (carVar.isPresent()){
            reservationService.deleteCustomer(id);
            model.addAttribute("messages","Customer deleted.");
            ArrayList<Customer> customerList = new ArrayList<>();
            customerList = reservationService.showAll();
            model.addAttribute("customers", customerList);
            return "listPage";
        }
        else {
            model.addAttribute("messages","There was an error deleting the value from the database.");
            return "listPage";
        }
    }

    @GetMapping(value = "/notes/{id}")
    public String notes(Model model, @PathVariable int id){
        model.addAttribute("Action","/notes/" + id);
        Optional<Customer> customerVar = reservationService.locateCustomer(id);
        if (customerVar.isPresent()){
            CustomerNotesForm customerNotesForm = new CustomerNotesForm();
            model.addAttribute("customerNotesform", customerNotesForm);
        }
        else{
            model.addAttribute("messages","There was an error updating values");
        }
        return "notesPage";
    }
    @PostMapping(value = "/notes/{id}")
    public String notesPost(Model model, CustomerNotesForm customerNotesForm, CustomerForm customerForm, @PathVariable int id){
        model.addAttribute("Action","/notes/" + id);
        model.addAttribute("messages","Successfully updated.");
        model.addAttribute("buttonVal", "Update");
        model.addAttribute("customerNotesForm", customerNotesForm);
        reservationService.saveCustomer(customerForm);
        return "notesPage";
    }

}

