package pl.javaCwiczenia2020.ui.gui;

public class RoomSelectionItem {

    private int id;
    private int number;

    public RoomSelectionItem(int id, int number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return String.format("%d", this.number);
    }

}
