package me.vinuvicho.footballmanager.exeption;

public class TeamNotFoundException extends IllegalStateException {

    public TeamNotFoundException(String e) {
        super(e);
    }

}
