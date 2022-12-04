package me.vinuvicho.footballmanager.exeption;

public class TooPoorTeam extends IllegalStateException {

    public TooPoorTeam(String e) {
        super(e);
    }
}
