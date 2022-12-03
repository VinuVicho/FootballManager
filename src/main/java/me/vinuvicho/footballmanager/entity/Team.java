package me.vinuvicho.footballmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

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
    @Min(0)
    private Long money;
    private Long commission;
    @Size(min = 3)
    private String name;
    private String logo;
    @Size(min = 2)
    private String city;
    @Size(min = 3)
    private String country;
    @Column(columnDefinition = "TEXT")
    private String about;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER)
    private List<Player> players = new java.util.ArrayList<>();

    public Team() {
    }
}
