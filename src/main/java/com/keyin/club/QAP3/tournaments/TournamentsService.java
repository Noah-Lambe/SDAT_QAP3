package com.keyin.club.QAP3.tournaments;

import com.keyin.club.QAP3.members.Members;
import com.keyin.club.QAP3.members.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class TournamentsService {
    @Autowired
    private TournamentsRepository tournamentsRepository;

    @Autowired
    private MembersRepository membersRepository;

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
        return tournamentsRepository.findByStartDate(date);
    }

    public List<Tournaments> searchTournaments(String date, String location) {
        if ((date == null || date.isEmpty()) &&
                (location == null || location.isEmpty())) {
            return List.of();
        }

        if (date != null && !date.isEmpty()) {
            try {
                LocalDate parsedDate = LocalDate.parse(date);
                return tournamentsRepository.findByStartDate(parsedDate);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.");
            }
        }

        return tournamentsRepository.findByLocation(location);
    }

    public Tournaments addMemberToTournament(Long tournamentId, Long memberId) {
        Tournaments tournament = tournamentsRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        Members member = membersRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Add the tournament to the member's list (owning side)
        tournament.getMembers().add(member);
        member.getTournaments().add(tournament);

        // Save the member (owning side)
        membersRepository.save(member);
        tournamentsRepository.save(tournament);

        // Optional: also add the member to the tournament for symmetry (not persisted)
        tournament.getMembers().add(member);

        return tournament;
    }

}
