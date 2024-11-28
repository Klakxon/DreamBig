package com.example.DreamBig.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("TRAINER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trainer extends User {

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> schedule;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    public Trainer(User user) {
        super();
    }
}
