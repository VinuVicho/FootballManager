package me.vinuvicho.footballmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Team implements Serializable {
    @SequenceGenerator(name = "team_sequence", sequenceName = "team_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_sequence")
    private Long id;
    private Long money;
    private Long commission;
    private String name;
    private String logo;
    private String city;
    private String country;
    @Column(columnDefinition = "TEXT")
    private String about;


    @OneToMany(fetch = FetchType.EAGER)
    private List<Player> players = new java.util.ArrayList<>();

    public Team() {
    }
}
