package me.vinuvicho.footballmanager.entity;

import lombok.*;

import javax.persistence.*;
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
    private String playerName;
    private String surname;
    private String photoUrl;
    private LocalDate birthDate;
    private LocalDate careerStarted;
    @Column(columnDefinition="TEXT")
    private String bio;

    @ManyToOne()
    private Team team;

    @Transient
    private Long transferCost;

    public Player() {
    }
}
