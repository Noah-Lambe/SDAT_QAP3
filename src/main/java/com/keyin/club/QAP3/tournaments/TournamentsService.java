package com.keyin.club.QAP3.tournaments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class TournamentsService {
    @Autowired
    private TournamentsRepository tournamentsRepository;

    public List<Tournaments> getAllTournaments() {
        return (List<Tournaments>) tournamentsRepository.findAll();
    }

    public Tournaments getTournamentById(long id) {
        return tournamentsRepository.findById(id).orElse(null);
    }

    public Tournaments createTournament(Tournaments tournament) {
        return tournamentsRepository.save(tournament);
    }

    public Tournaments updateTournament(long id, Tournaments updatedTournament) {
        return tournamentsRepository.findById(id)
                .map(tournament -> {
                    tournament.setLocation(updatedTournament.getLocation());
                    tournament.setStartDate(updatedTournament.getStartDate());
                    tournament.setEndDate(updatedTournament.getEndDate());
                    tournament.setEntryFee(updatedTournament.getEntryFee());
                    tournament.setCashPrize(updatedTournament.getCashPrize());

                    return tournamentsRepository.save(tournament);
                })
                .orElseGet(() -> {
                    updatedTournament.setId(id);
                    return tournamentsRepository.save(updatedTournament);
                });
    }

    public void deleteTournament(long id) {
        tournamentsRepository.deleteById(id);
    }

    public List<Tournaments> findByLocation(String location) {
        return tournamentsRepository.findByLocation(location);
    }

    public List<Tournaments> findByTournamentDate(java.time.LocalDate date) {
        return tournamentsRepository.findByTournamentStartDate(date);
    }

    public List<Tournaments> searchTournaments(String date, String location) {
        if ((date == null || date.isEmpty()) &&
                (location == null || location.isEmpty())) {
            return List.of();
        }

        if (date != null && !date.isEmpty()) {
            try {
                LocalDate parsedDate = LocalDate.parse(date);
                return tournamentsRepository.findByTournamentStartDate(parsedDate);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.");
            }
        }

        return tournamentsRepository.findByLocation(location);
    }

}
