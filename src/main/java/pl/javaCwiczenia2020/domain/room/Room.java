package pl.javaCwiczenia2020.domain.room;

public class Room {

    private final int number;
    private final BedType[] bedType;

    Room(int number, BedType[] bedTypes) {
        this.number = number;
        this.bedType = bedTypes;
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < bedType.length; i++) {
            info.append("\n").append((i + 1)).append(bedType[i]);
        }
        return String.format("Utworzono pokój nr: %d \nz %d łóżkami:", this.number, this.bedType.length) + info;
    }
}
