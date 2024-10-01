package com.example.DreamBig.controller;
import com.example.DreamBig.dto.ClubDTO;
import com.example.DreamBig.exception.InvalidInputException;
import com.example.DreamBig.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private List<ClubDTO> clubs = new ArrayList<>();

    @GetMapping
    public List<ClubDTO> getAllClubs() {
        return clubs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable Long id) {
        return clubs.stream()
                .filter(club -> club.getId().equals(id))
                .findFirst()
                .map(club -> new ResponseEntity<>(club, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Club not found with ID: " + id));
    }

    @PostMapping
    public ResponseEntity<ClubDTO> createClub(@RequestBody ClubDTO clubDTO) {
        if (clubDTO.getName() == null || clubDTO.getName().isEmpty()) {
            throw new InvalidInputException("Club name cannot be empty");
        }
        clubs.add(clubDTO);
        return new ResponseEntity<>(clubDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubDTO> updateClub(@PathVariable Long id, @RequestBody ClubDTO updatedClub) {
        for (ClubDTO club : clubs) {
            if (club.getId().equals(id)) {
                club.setName(updatedClub.getName());
                club.setAddress(updatedClub.getAddress());
                club.setHasPool(updatedClub.isHasPool());
                club.setHasGym(updatedClub.isHasGym());
                club.setHasCardioZone(updatedClub.isHasCardioZone());
                return new ResponseEntity<>(club, HttpStatus.OK);
            }
        }
        throw new ResourceNotFoundException("Club not found with ID: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable Long id) {
        boolean removed = clubs.removeIf(club -> club.getId().equals(id));
        if (removed) {
            return new ResponseEntity<>("Club with ID " + id + " deleted.", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Club not found with ID: " + id);
        }
    }
}
