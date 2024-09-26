package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.ClubEntity;
import com.example.DreamBig.repository.ClubRepository;
import com.example.DreamBig.service.interfaces.ClubService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubEntity> getAllClubs() {
        return clubRepository.findAll();
    }

    @Override
    public ClubEntity getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club not found with id " + id));
    }

    @Override
    public ClubEntity createClub(ClubEntity club) {
        return clubRepository.save(club);
    }

    @Override
    public ClubEntity updateClub(Long id, ClubEntity updatedClub) {
        return clubRepository.findById(id)
                .map(club -> {
                    club.setName(updatedClub.getName());
                    club.setAddress(updatedClub.getAddress());
                    club.setHasPool(updatedClub.isHasPool());
                    club.setHasGym(updatedClub.isHasGym());
                    club.setHasCardioZone(updatedClub.isHasCardioZone());
                    return clubRepository.save(club);
                })
                .orElseThrow(() -> new EntityNotFoundException("Club not found with id " + id));
    }

    @Override
    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }
}