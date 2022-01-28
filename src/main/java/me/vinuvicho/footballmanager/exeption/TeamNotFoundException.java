package me.vinuvicho.footballmanager.exeption;

import org.springframework.data.crossstore.ChangeSetPersister;

public class TeamNotFoundException extends ChangeSetPersister.NotFoundException {

    public TeamNotFoundException(String e) {
        System.out.println(e);
    }

}
