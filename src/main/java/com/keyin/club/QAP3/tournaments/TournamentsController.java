package com.keyin.club.QAP3.tournaments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tournaments")
public class TournamentsController {
    @Autowired
    private TournamentsService tournamentsService;

    @GetMapping
    public ResponseEntity<List<Tournaments>> getAllTournaments() {
        try {
            List<Tournaments> tournaments = tournamentsService.getAllTournaments();
            return ResponseEntity.ok(tournaments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournaments> getTournamentById(long id) {
        try {
            Tournaments tournament = tournamentsService.getTournamentById(id);
            if (tournament != null) {
                return ResponseEntity.ok(tournament);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/tournament_search")
    public ResponseEntity<List<Tournaments>> searchTournaments(
            @RequestParam(value = "tournament_date", required = false) String date,
            @RequestParam(value = "tournament_location", required = false) String location) {

        if ((date == null || date.isEmpty()) && (location == null || location.isEmpty())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        try {
            List<Tournaments> results = tournamentsService.searchTournaments(date, location);
            if (results.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.ok(results);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Tournaments> createTournament(@RequestBody Tournaments tournament) {
        try {
            Tournaments createdTournament = tournamentsService.createTournament(tournament);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTournament);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournaments> updateTournament(@PathVariable long id, @RequestBody Tournaments updatedTournament) {
        try {
            Tournaments tournament = tournamentsService.updateTournament(id, updatedTournament);
            if (tournament != null) {
                return ResponseEntity.ok(tournament);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable long id) {
        try {
            tournamentsService.deleteTournament(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
}
