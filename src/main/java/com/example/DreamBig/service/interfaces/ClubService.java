package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.ClubEntity;

import java.util.List;

public interface ClubService {

    List<ClubEntity> getAllClubs();

    ClubEntity getClubById(Long id);

    ClubEntity createClub(ClubEntity club);

    ClubEntity updateClub(Long id, ClubEntity updatedClub);

    void deleteClub(Long id);
}

