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
        List<Player> players = playerRepo.findAll();
        for (Player p : players) {
            if (p.getTeamName() == null) p.setTransferCost(0L);
            else p.setTransferCost(calculateTransferCost
                    (p, teamRepo.getTeamByName(p.getTeamName()).get().getCommission()));
        }
        return players;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Player updatePlayer(Player player) {
        Player databasePlayer = playerRepo.getPlayerById(player.getId());
        if (Objects.equals(databasePlayer.getTeamName(), player.getTeamName())) {   //team not changed
            return playerRepo.save(player);
        }
        if (player.getTeamName() == null) {                                         //remove team
            playerRepo.deletePlayerFromAnyTeam(player.getId());
            return playerRepo.save(player);
        }
        Team transferTo = teamRepo.getTeamByName(player.getTeamName())
                .orElseThrow(() -> new TeamNotFoundException("Cannot transfer to team, that doesnt exist"));
        if (databasePlayer.getTeamName() != null) {                                 //team changed
            Team transferFrom = teamRepo.getTeamByName(databasePlayer.getTeamName()).get();
            Long transferCost = calculateTransferCost(databasePlayer, transferFrom.getCommission());
            if (transferTo.getMoney() < transferCost) throw new TooPoorTeam("Team too poor to buy this guy");

            transferTo.setMoney(transferTo.getMoney() - transferCost);
            transferFrom.setMoney(transferFrom.getMoney() + transferCost);

            List<Player> players = transferTo.getPlayers();
            players.add(player);
            transferTo.setPlayers(players);
            playerRepo.save(player);
            teamRepo.save(transferFrom);
            playerRepo.deletePlayerFromAnyTeam(player.getId());
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
        Player player = playerRepo.findById(id).orElseThrow(() -> new PlayerNotFoundException("No Player found"));
        player.setTransferCost((player.getTeamName() == null) ? 0 :
                calculateTransferCost(player, teamRepo.getTeamByName(player.getTeamName()).get().getCommission()));
        return player;
    }

    public void deletePlayer(Long id) {
        playerRepo.deletePlayerFromAnyTeam(id);
        playerRepo.deleteById(id);
    }

    public List<Player> getTeamPlayers(Long id) {
        return playerRepo.getPlayersByTeamName(teamRepo.getTeamById(id).get().getName());
    }
}
