package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.Club;

import java.util.List;

public interface ClubService {

    List<Club> getAllClubs();

    Club getClubById(Long id);

    Club createClub(Club club);

    Club updateClub(Long id, Club updatedClub);

    void deleteClub(Long id);
}

