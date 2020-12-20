package pl.javaCwiczenia2020.domain.room;

public class Room {

    private final int id;
    private final int number;
    private final BedType[] bedType;

    Room(int id, int number, BedType[] bedTypes) {
        this.id = id;
        this.number = number;
        this.bedType = bedTypes;
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < bedType.length; i++) {
            info.append("\n").append((i + 1)).append(" ").append(bedType[i]);
        }
        return String.format("%d Pokój nr: %d \nz %d łóżkami: ",
                this.id,
                this.number,
                this.bedType.length) + info;
    }

    public String toCSV() {

        String [] bedTypesStringArray = new String[this.bedType.length];

        int i = 0;
        for (BedType bedType : this.bedType) {
            bedTypesStringArray[i] = bedType.toString();
            i++;
        }
        String bedTypesData = String.join("#", bedTypesStringArray);

        return String.format("%d,%d,%s%s",
                this.id,
                this.number,
                bedTypesData,
                System.getProperty("line.separator"));
    }

    public int getId() {
        return this.id;
    }
}
