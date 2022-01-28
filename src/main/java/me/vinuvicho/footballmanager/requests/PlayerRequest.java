package me.vinuvicho.footballmanager.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.vinuvicho.footballmanager.entity.Player;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

    //оці класи мали б кидатись на запит, але хз

@Getter
@Setter
@ToString
public class PlayerRequest implements Serializable {
    private Long id;
    private String playerName;
    private String surname;
    private String photoUrl;
    private LocalDate age;
    private LocalDate careerStarted;

    private PlayerTeam team;

    private Long transferCost;

    public PlayerRequest(Player player) {
        this.id = player.getId();
        this.playerName = player.getPlayerName();
        this.surname = player.getSurname();
        this.photoUrl = player.getPhotoUrl();
        this.age = player.getBirthDate();
        this.careerStarted = player.getCareerStarted();
//        if (player.getTeam() != null) {
//            this.team = new PlayerTeam(player.getTeam());
//            calculateTransfer();
//        }
//        else transferCost = 0L;
    }

    public void calculateTransfer() {
        long experience = ChronoUnit.MONTHS.between(careerStarted, LocalDate.now());
        transferCost = experience * 100000 / age;
        if (team != null) transferCost = transferCost + transferCost / 100 * team.getCommission();
    }
}
