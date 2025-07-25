package com.keyin.club.QAP3.tournaments;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentsRepository extends CrudRepository<Tournaments, Long> {
     List<Tournaments> findByStartDate(LocalDate startDate);
     List<Tournaments> findByLocation(String location);
}
