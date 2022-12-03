package me.vinuvicho.footballmanager.repository;

import me.vinuvicho.footballmanager.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PlayerRepoTest {

    @Autowired
    private PlayerRepo playerRepo;

    @AfterEach
    void tearDown() {
//        Code that should run after every test
    }
}