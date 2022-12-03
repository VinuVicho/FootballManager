package me.vinuvicho.footballmanager.dto;

import lombok.*;
import me.vinuvicho.footballmanager.entity.Player;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private Long id;
    private String playerName;
    private String surname;
    private String photoUrl;
    private LocalDate birthDate;
    private LocalDate careerStarted;
    private String teamName;
    private String bio;
    private Long transferCost;

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.playerName = player.getPlayerName();
        this.surname = player.getSurname();
        this.photoUrl = player.getPhotoUrl();
        this.birthDate = player.getBirthDate();
        this.careerStarted = player.getCareerStarted();
        if (player.getTeam() != null)
            this.teamName = player.getTeam().getName();
        this.bio = player.getBio();
        this.transferCost = player.getTransferCost();
    }
}
