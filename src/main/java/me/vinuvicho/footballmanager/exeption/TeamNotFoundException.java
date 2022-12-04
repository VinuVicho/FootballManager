package me.vinuvicho.footballmanager.exeption;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(String e) {
        super(e);
    }
}
