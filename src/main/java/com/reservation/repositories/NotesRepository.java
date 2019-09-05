package com.reservation.repositories;

import com.reservation.entity.CustomerNotes;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NotesRepository extends PagingAndSortingRepository<CustomerNotes, Integer> {
}
