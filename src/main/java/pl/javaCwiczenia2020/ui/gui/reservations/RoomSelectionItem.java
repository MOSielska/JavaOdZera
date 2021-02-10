package pl.javaCwiczenia2020.ui.gui.reservations;

public class RoomSelectionItem {

    private long id;
    private int number;

    public RoomSelectionItem(long id, int number) {
        this.id = id;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return String.format("%d", this.number);
    }

}
