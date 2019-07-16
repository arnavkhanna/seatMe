package com.reservation.repositories;

import com.reservation.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<Customer, Integer> {
}
