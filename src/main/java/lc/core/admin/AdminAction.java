package lc.core.admin;

import lc.core.entidades.Jugador;

public class AdminAction<T> {

    private final AdminActionType type;
    private final Jugador from;
    private final Jugador to;
    private T value;

    public AdminAction(AdminActionType type, Jugador sender, Jugador recieved) {
        this.type = type;
        this.from = sender;
        this.to = recieved;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public AdminActionType getType() {
        return type;
    }

    public Jugador getTo() {
        return to;
    }

    public Jugador getFrom() {
        return from;
    }
}
