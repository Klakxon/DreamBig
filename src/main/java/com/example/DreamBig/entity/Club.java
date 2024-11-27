package com.example.DreamBig.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clubs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private boolean hasPool;

    private boolean hasGym;

    private boolean hasCardioZone;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Trainer> trainers;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Session> sessions;
}