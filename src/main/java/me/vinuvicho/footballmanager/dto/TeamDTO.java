package me.vinuvicho.footballmanager.dto;

import lombok.*;
import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.entity.Team;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.UniqueConstraint;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {

    private Long id;
    private Long money;
    private Long commission;
    private String name;
    private String logo;
    private String city;
    private String country;
    private String about;
    private List<PlayerDTO> players = new java.util.ArrayList<>();

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.money = team.getMoney();
        this.commission = team.getCommission();

        this.name = team.getName();
        this.logo = team.getLogo();
        this.city = team.getCity();
        this.country = team.getCountry();
        this.about = team.getAbout();
        List<Player> teamPlayers = team.getPlayers();
        for (Player p: teamPlayers) {
            players.add(new PlayerDTO(p));
        }
    }
}
