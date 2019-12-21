package com.reservation.controller;

import com.reservation.entity.Customer;
import com.reservation.entity.CustomerNotes;
import com.reservation.form.CustomerForm;
import com.reservation.form.CustomerNotesForm;
import com.reservation.services.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Optional;


@Controller
public class WebController {

    private final Logger logger = LogManager.getLogger(getClass());

    private final ReservationService reservationService;

    public WebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    /**
     * posts home page for app
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {

        logger.info("request to index page");


        model.addAttribute("customerform", new CustomerForm());
        return "getPage";
    }

    @PostMapping(value = "/")
    public String post(@ModelAttribute CustomerForm customerForm, Model model){
        logger.info(customerForm);
        ArrayList<Customer> nameList = new ArrayList<Customer>();
        Iterable<Customer> customerVar = reservationService.findByName(customerForm.getName());
        Iterator<Customer> customerIterator = customerVar.iterator();
        while(customerIterator.hasNext()){
            nameList.add(customerIterator.next());
        }



        if (nameList.size() > 0) {
            model.addAttribute("customers", nameList);
            return "listPage";
        }
        else{
            model.addAttribute("customerform", new CustomerForm());
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
            CustomerForm customerForm = new CustomerForm(customerVar.get());
            model.addAttribute("customerform", customerForm);
        }
        else{
            model.addAttribute("messages","There was an error updating values");
        }

        model.addAttribute("buttonVal", "Update");
        return "editPage";
    }
    @PostMapping(value = "/edit/{id}")
    public String editPost(Model model, CustomerForm customerForm, @PathVariable int id, RedirectAttributes redirAttrs) {
        model.addAttribute("Action","/edit/" + id);
        model.addAttribute("messages","Successfully updated.");
        model.addAttribute("buttonVal", "Update");
        model.addAttribute("customerform", customerForm);
        customerForm.setId(id);
        reservationService.saveCustomer(customerForm);
        redirAttrs.addFlashAttribute("messages", "Successfully updated customer");
        return "redirect:/all/";
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

    /**
     * Retrieve all of the notes for the customerId
     *
     * @param model
     * @param customerId
     * @return
     */
    @GetMapping(value = "/notes/{customerId}")
    public String retrieveAllNotes(Model model, @PathVariable int customerId) {

        model.addAttribute("Action","/notes/" + customerId);

        Optional<Customer> customerVar = reservationService.locateCustomer(customerId);

        if (customerVar.isPresent()){
            Customer customer = customerVar.get();

            model.addAttribute("customer", customer);
            model.addAttribute("customerNotesForm", new CustomerNotesForm());

        } else{
            model.addAttribute("messages","There was an error updating notes");
        }

        ArrayList<CustomerNotes> customerNotesList = reservationService.showAllNotes();
        ArrayList<CustomerNotes> filteredCustomerNotesList = new ArrayList<>();
        int i = 0;
        while (i<(customerNotesList.size())){
            CustomerNotes customerNotes = customerNotesList.get(i);
            if (customerNotes.getCustomerId() == customerId){
                filteredCustomerNotesList.add(customerNotes);
            }
            i++;
        }

        model.addAttribute("notes", filteredCustomerNotesList);
        model.addAttribute("customerId", customerId);

        return "notesPage";
    }


    @GetMapping(value = "/addnotes/{id}")
    public String addNotes(@ModelAttribute CustomerNotesForm customerNotesForm, Model model, @PathVariable int id) {
        logger.info(customerNotesForm);
        Optional<Customer> customerVar = reservationService.locateCustomer(id);
        if (customerVar.isPresent()){
            Customer customer = customerVar.get();
            model.addAttribute("customer", customer);
            model.addAttribute("customerNotesForm", customerNotesForm);
        }
        else{
            model.addAttribute("messages","There was an error updating notes");
        }
        model.addAttribute("buttonVal", "Add");
        model.addAttribute("customerNotesForm", new CustomerNotesForm());
        model.addAttribute("Action","/addnotes/" + id);
        return "addNotesPage";

    }

    @PostMapping(value = "/addnotes/{customerId}")
    public String addNotesResults(RedirectAttributes redirAttrs, @ModelAttribute CustomerNotesForm customerNotesForm, Model model, @PathVariable int customerId) {

        customerNotesForm.setCustomerId(customerId);

        logger.info(customerNotesForm);

        CustomerNotes customer = reservationService.saveNotes(customerNotesForm);

        if (customer == null){
            model.addAttribute("Action","/addnotes/" + customerId);
            model.addAttribute("buttonVal", "Add");
            model.addAttribute("customerNotesForm", customerNotesForm);

            model.addAttribute("messages", "There was an error adding the values to the database.");
            return "addNotesPage";
        } else {
            // show message on the redirect page
            redirAttrs.addFlashAttribute("messages", "Successfully added new note");

            return "redirect:/notes/" + customerId;
        }
    }

    @GetMapping(value = "/notesedit/{id}/{notesid}")
    public String editNotes(Model model, @PathVariable int id, @PathVariable int notesid){
        model.addAttribute("Action","/notesedit/" + id + "/" + notesid);
        Optional<Customer> customerVar = reservationService.locateCustomer(id);
        if (customerVar.isPresent()){
            Customer customer = customerVar.get();
            model.addAttribute("customer", customer);
        }
        Optional<CustomerNotes> customerNotesVar = reservationService.locateNotes(notesid);
        if (customerNotesVar.isPresent()){
            CustomerNotesForm customerNotesForm = new CustomerNotesForm(customerNotesVar.get());
            model.addAttribute("customerNotesForm", customerNotesForm);
        }
        else{
            model.addAttribute("messages","There was an error updating values");
        }


        return "editNotesPage";
    }


    @PostMapping(value = "/notesedit/{id}/{notesid}")
    public String editNotesResults(RedirectAttributes redirAttrs, CustomerNotesForm customerNotesForm, Model model, @PathVariable int id, @PathVariable int notesid) {
        customerNotesForm.setCustomerId(id);
        customerNotesForm.setId(notesid);
        reservationService.saveNotes(customerNotesForm);

        redirAttrs.addFlashAttribute("messages", "Successfully updated note");
        return "redirect:/notes/" + id;
    }

    @GetMapping(value = "/notesremove/{id}/{notesid}")
    public String deleteNotes(Model model, @PathVariable int id, @PathVariable int notesid, RedirectAttributes redirAttrs){
        Optional<CustomerNotes> notesVar = reservationService.locateNotes(notesid);
        if (notesVar.isPresent()){
            reservationService.deleteNotes(notesid);
            redirAttrs.addFlashAttribute("messages", "Notes deleted");

            return "redirect:/notes/" + id;
        }
        else {
            redirAttrs.addFlashAttribute("messages", "Successfully updated note");
            return "redirect:/notes/" + id;
        }
    }
}
