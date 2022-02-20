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

    public Player addPlayer(Player player) throws TeamNotFoundException {
        System.out.println(player);     //FIXME: delete

        player = validatePlayer(player);
        if (player.getTeam() == null) {
            return playerRepo.save(player);
        }
        Team team = player.getTeam();
        player = playerRepo.save(player);
        List<Player> players = team.getPlayers();
        players.add(player);
        team.setPlayers(players);
        teamRepo.save(team);

        System.out.println(player);     //FIXME: delete
        return player;
    }

    public List<Player> findAllPlayers() {
        List<Player> players = playerRepo.findAll();
        for (Player p : players) {      //not needed
            if (p.getTeam() == null) p.setTransferCost(0L);
            else p.setTransferCost(calculateTransferCost(p));
        }
        return players;
    }

    public Player updatePlayer(Player player) throws TooPoorTeam, TeamNotFoundException {
        Player databasePlayer = playerRepo.getPlayerById(player.getId());
        System.out.println("Database playerTeam " + databasePlayer.getTeam());
        System.out.println("Updated playerTeam " + player.getTeam());
        if (Objects.equals(databasePlayer.getTeam().getName(), player.getTeam().getName())) {   //team not changed
            return playerRepo.save(player);
        }
        if (player.getTeam() == null) {                                         //remove team
            playerRepo.deletePlayerFromAnyTeam(player.getId());
            return playerRepo.save(player);
        }
        Team transferTo = player.getTeam();
        if (databasePlayer.getTeam() != null) {                                 //team changed
            Team transferFrom = databasePlayer.getTeam();
            Long transferCost = calculateTransferCost(databasePlayer);
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

    public Player validatePlayer(Player player) {
        //TODO: validation
        return player;
    }

    public Long calculateTransferCost(Player player, Long commission) {
        long experience = ChronoUnit.MONTHS.between(player.getCareerStarted(), LocalDate.now());
        long transferCost = experience * 100000 / ChronoUnit.YEARS.between(player.getBirthDate(), LocalDate.now());
        return transferCost + transferCost / 100 * commission;
    }

    public Long calculateTransferCost(Player player) {
        long experience = ChronoUnit.MONTHS.between(player.getCareerStarted(), LocalDate.now());
        long transferCost = experience * 100000 / ChronoUnit.YEARS.between(player.getBirthDate(), LocalDate.now());
        return transferCost + transferCost / 100 * player.getTeam().getCommission();
    }

    public Player findPlayerById(Long id) throws PlayerNotFoundException {
        Player player = playerRepo.findById(id).orElseThrow(() -> new PlayerNotFoundException("No Player found"));
        player.setTransferCost((player.getTeam() == null) ? 0 : calculateTransferCost(player));
        return player;
    }

    public void deletePlayer(Long id) {
        playerRepo.deletePlayerFromAnyTeam(id);
        playerRepo.deleteById(id);
    }

    public List<Player> getTeamPlayers(Long id) throws TeamNotFoundException {
        Team team = teamRepo.getTeamById(id).orElseThrow(() -> new TeamNotFoundException("Team not found"));
        List<Player> players = playerRepo.getPlayersByTeamName(team.getName());
        players.forEach(p -> p.setTransferCost(calculateTransferCost(p)));      //not needed
        return players;
    }
}
