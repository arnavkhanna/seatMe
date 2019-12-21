package com.reservation.repositories;

import com.reservation.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservationRepository extends PagingAndSortingRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c where c.name = :name")
    Optional<Customer> findCustomerByName(@Param("name") String name);

    Iterable<Customer> findAllByNameContainingIgnoreCase(@Param("name") String name);


}
