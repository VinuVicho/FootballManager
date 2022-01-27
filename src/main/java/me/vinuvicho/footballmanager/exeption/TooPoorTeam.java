package me.vinuvicho.footballmanager.exeption;

import org.springframework.data.crossstore.ChangeSetPersister;

public class TooPoorTeam  extends ChangeSetPersister.NotFoundException {

    public TooPoorTeam(String e) {

    }
}
