package me.vinuvicho.footballmanager.service;

import lombok.AllArgsConstructor;
import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.entity.Team;
import me.vinuvicho.footballmanager.exeption.PlayerNotFoundException;
import me.vinuvicho.footballmanager.exeption.TeamNotFoundException;
import me.vinuvicho.footballmanager.exeption.TooPoorTeam;
import me.vinuvicho.footballmanager.repository.PlayerRepo;
import me.vinuvicho.footballmanager.repository.TeamRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final TeamRepo teamRepo;

    public Player addPlayer(Player player) {        //TODO: validate?
        if (player.getTeamName() == null) {
            return playerRepo.save(player);
        }
        Team team = teamRepo.getTeamByName(player.getTeamName())
                .orElseThrow(() -> new TeamNotFoundException("Team not found"));
        player = playerRepo.save(player);
        List<Player> players = team.getPlayers();
        players.add(player);
        team.setPlayers(players);
        teamRepo.save(team);
        return player;
    }

    public List<Player> findAllPlayers() {
        return playerRepo.findAll();
    }

    public Player updatePlayer(Player player) {
        Player databasePlayer = playerRepo.getPlayerById(player.getId());
        if (Objects.equals(databasePlayer.getTeamName(), player.getTeamName())) {
            return playerRepo.save(player);
        }
        Team transferTo = teamRepo.getTeamByName(player.getTeamName())
                .orElseThrow(() -> new TeamNotFoundException("Cannot transfer to unexciting team"));
        if (databasePlayer.getTeamName() != null) {
            Team transferFrom = teamRepo.getTeamByName(databasePlayer.getTeamName())
                    .orElseThrow(() -> new TeamNotFoundException("HOW U GOT THIS"));
            Long transferCost = calculateTransferCost(databasePlayer, transferFrom.getCommission());
            if (transferTo.getMoney() < transferCost) throw new TooPoorTeam("Team too poor to buy this guy");

            transferTo.setMoney(transferTo.getMoney() - transferCost);
            transferFrom.setMoney(transferFrom.getMoney() + transferCost);
            playerRepo.save(player);
            List<Player> players = transferFrom.getPlayers();
            players.remove(databasePlayer);
            transferFrom.setPlayers(players);
            teamRepo.save(transferFrom);
            players = transferTo.getPlayers();
            players.add(player);
            transferTo.setPlayers(players);
            teamRepo.save(transferTo);
            return player;
        }
        playerRepo.save(player);
        List<Player> players = transferTo.getPlayers();
        players.add(player);
        transferTo.setPlayers(players);
        teamRepo.save(transferTo);
        return player;
    }

    public Long calculateTransferCost(Player player, Long teamCommission) {
        long experience = ChronoUnit.MONTHS.between(player.getCareerStarted(), LocalDate.now());
        long transferCost = experience * 100000 / player.getAge();
        return transferCost + transferCost / 100 * teamCommission;
    }

    public Player findPlayerById(Long id) {
        return playerRepo.findById(id).orElseThrow(() -> new PlayerNotFoundException("No Player found"));
    }

    public void deletePlayer(Long id) {
        playerRepo.deleteById(id);
    }
}
