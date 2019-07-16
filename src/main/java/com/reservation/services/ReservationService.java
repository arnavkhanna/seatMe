package com.reservation.services;

import com.reservation.entity.Customer;
import com.reservation.form.CustomerForm;
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


    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Optional<Customer> locateCustomer(int id) {

        logger.info("Locating reservation: {}", id);

        return reservationRepository.findById(id);
    }
    public Customer saveCustomer(CustomerForm customerForm) {
        Customer customer = new Customer(customerForm);
        logger.info("Locating reservation: {}", customerForm);

        return reservationRepository.save(customer);
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

}
