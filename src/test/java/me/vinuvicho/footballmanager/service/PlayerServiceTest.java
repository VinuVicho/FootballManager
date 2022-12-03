package me.vinuvicho.footballmanager.service;

import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.repository.PlayerRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepo playerRepo;
    private PlayerService playerServiceTest;

    @BeforeEach
    void setUp() {
        playerServiceTest = new PlayerService(playerRepo, null);
    }

    @Test
    void addPlayer() {
//        Given
        Player player = new Player("Name", "Surname");
//        When

        playerRepo.save(player);
//        then

    }

    @Test
    void canGetAllPlayers() {
//        when
        playerServiceTest.findAllPlayers();
//        then
        verify(playerRepo).findAll();
    }

    @Test
    @Disabled
    void deletePlayer() {
    }
}