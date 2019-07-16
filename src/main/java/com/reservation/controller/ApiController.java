package com.reservation.controller;

import com.reservation.entity.Customer;
import com.reservation.form.CustomerForm;
import com.reservation.services.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ApiController {

    private final Logger logger = LogManager.getLogger(getClass());

    private final ReservationService reservationService;


    @Autowired
    public ApiController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get(@PathVariable int id) {

        logger.info("New  Request {}", id);

        Optional<Customer> customerVar = reservationService.locateCustomer(id);
        if (customerVar.isPresent()){
            return new ResponseEntity<>(customerVar.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Customer not found by ID.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> put(@RequestBody CustomerForm customerForm) {

        logger.info("New  Request {}", customerForm);

        Customer customer = reservationService.saveCustomer(customerForm);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable int id) {

        logger.info("New  Request {}", id);

        Optional<Customer> customerVar = reservationService.locateCustomer(id);
        if (customerVar.isPresent()){
            reservationService.deleteCustomer(id);
            return new ResponseEntity<>("Customer deleted.", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Customer not found by ID.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (value = "/customer/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> show(){
        logger.info("New Request {}");
        return new ResponseEntity<>(reservationService.showAll().toString(), HttpStatus.OK);

    }


}
