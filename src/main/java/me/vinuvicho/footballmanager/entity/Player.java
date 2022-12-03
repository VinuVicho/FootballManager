package me.vinuvicho.footballmanager.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Player implements Serializable {
    @SequenceGenerator(name = "player_sequence", sequenceName = "player_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_sequence")
    private Long id;
    @NotNull
    @Size(min = 3)
    private String playerName;
    @NotNull
    @Size(min = 3)
    private String surname;
    private String photoUrl;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private LocalDate careerStarted;
    @Column(columnDefinition="TEXT")
    private String bio;

    @ManyToOne()
    private Team team;

    @Transient
    private Long transferCost;

    public Player() {
    }

    public Player(String playerName, String surname) {      //For tests
        this.playerName = playerName;
        this.surname = surname;
    }
}
