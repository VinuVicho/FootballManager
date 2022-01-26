package me.vinuvicho.footballmanager.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.vinuvicho.footballmanager.entity.Team;

@Getter
@Setter
@AllArgsConstructor
public class PlayerTeam {
    private Long id;
    private String name;
    private Long commission;

    public PlayerTeam() {
    }

    public PlayerTeam(Team team) {
        this.commission = team.getCommission();
        this.name = team.getName();
        this.id = team.getId();
    }
}
