package com.reservation.services;

import com.reservation.entity.Customer;
import com.reservation.entity.CustomerNotes;
import com.reservation.form.CustomerForm;
import com.reservation.form.CustomerNotesForm;
import com.reservation.repositories.NotesRepository;
import com.reservation.repositories.ReservationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class ReservationService {

    private final Logger logger = LogManager.getLogger(getClass());

    private ReservationRepository reservationRepository;
    private NotesRepository notesRepository;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, NotesRepository notesRepository) {
        this.reservationRepository = reservationRepository;
        this.notesRepository = notesRepository;
    }

    /**
     * for locating customer. need to change so its search by name not search by id
     * search by element???
     *
     * @param id
     * @return
     */
    public Optional<Customer> locateCustomer(int id) {

        logger.info("Locating reservation: {}", id);

        return reservationRepository.findById(id);
    }


    public Optional<Customer> findByName(String name) {

        logger.info("Locating reservation: {}", name);

        return reservationRepository.findCustomerByName(name);
    }


    /**
     * Saves customer to db
     *
     * @param customerForm
     * @return
     */
    public Customer saveCustomer(CustomerForm customerForm) {
        Customer customer = new Customer(customerForm);
        logger.info("Locating reservation: {}", customerForm);

        return reservationRepository.save(customer);
    }

    /**
     * Saves notes to db
     * @param customerNotesForm
     * @return
     */
    public CustomerNotes saveNotes(CustomerNotesForm customerNotesForm){
        CustomerNotes customerNotes = new CustomerNotes(customerNotesForm);
        logger.info("Locating notes{}", customerNotesForm);

        return notesRepository.save(customerNotes);
    }

    public void deleteCustomer(int id) {
        logger.info("Deleting reservation: {}", id);
        reservationRepository.deleteById(id);
    }
    public ArrayList<Customer> showAll(){
        logger.info("Displaying: all");
        ArrayList<Customer> customerList = new ArrayList<>();
        Iterable<Customer> customers = reservationRepository.findAll();
        Iterator<Customer> customerIterator = customers.iterator();
        while(customerIterator.hasNext()){
            customerList.add(customerIterator.next());
        }
        return customerList;
    }

    public ArrayList<CustomerNotes> showAllNotes(){
        logger.info("Displaying all");
        ArrayList<CustomerNotes> notesList = new ArrayList<>();
        Iterable<CustomerNotes> notes = notesRepository.findAll();
        Iterator<CustomerNotes> notesIterator = notes.iterator();
        while(notesIterator.hasNext()){
            notesList.add(notesIterator.next());
        }
        return notesList;
    }

    public Optional<CustomerNotes> locateNotes(int id) {

        logger.info("Locating reservation: {}", id);

        return notesRepository.findById(id);
    }

    public void deleteNotes(int id) {
        logger.info("Deleting reservation: {}", id);
        notesRepository.deleteById(id);
    }



}
