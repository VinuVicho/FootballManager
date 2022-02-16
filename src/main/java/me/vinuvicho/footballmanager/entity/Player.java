package me.vinuvicho.footballmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@ToString
public class Player implements Serializable {
    @SequenceGenerator(name = "player_sequence", sequenceName = "player_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_sequence")
    private Long id;
    private String playerName;
    private String surname;
    private String photoUrl;
    private LocalDate birthDate;
    private LocalDate careerStarted;
    private String teamName;
    @Column(columnDefinition="TEXT")
    private String bio;

    @Transient
    private Long transferCost;

    public Player() {
    }
}
