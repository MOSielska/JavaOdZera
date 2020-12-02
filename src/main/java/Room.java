public class Room {

    private int number;
    private boolean isFree;
    private BedType[] bedType;

    public Room(int number, BedType[] bedTypes) {
        this.number = number;
        this.bedType = bedTypes;
        this.isFree = true;
    }

    public String getInfo() {
        String info = "";
        for (int i = 0; i < bedType.length; i++) {
            info += ("\n" + (i + 1) + ": " + bedType[i]);
        }
        return String.format("Utworzono pokój nr: %d \nz %d łóżkami:", this.number, this.bedType.length) + info;
    }
}
