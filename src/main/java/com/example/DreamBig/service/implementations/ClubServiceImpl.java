package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.Club;
import com.example.DreamBig.repository.ClubRepository;
import com.example.DreamBig.service.interfaces.ClubService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    @Override
    @Cacheable(value = "clubs", key = "#id")
    public Club getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club not found with id " + id));
    }

    @Override
    @CachePut(value = "clubs", key = "#club.id")
    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    @Override
    @CachePut(value = "clubs", key = "#id")
    public Club updateClub(Long id, Club updatedClub) {
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
    @CacheEvict(value = "clubs", key = "#id")
    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }
}
