package me.vinuvicho.footballmanager.repository;

import me.vinuvicho.footballmanager.entity.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PlayerRepo extends JpaRepository<Player, Long> {

    @EntityGraph(attributePaths = {"team.name"})
    Player getPlayerById(Long id);

    List<Player> getPlayersByTeamName(String teamName);

    @Modifying
    @Query(value = "DELETE FROM team_players WHERE players_id = ?1", nativeQuery = true)
    void deletePlayerFromAnyTeam(Long id);
}
