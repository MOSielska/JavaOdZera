public class Room {

    private int number;
   // private int bedsNumber;
    private boolean isFree;
    private BeedType bedType;

    public Room(int number, BeedType bedType) {
        this.number = number;
        //this.bedsNumber = bedsNumber;
        this.bedType = bedType;
        this.isFree = true;
    }

    public String getInfo() {
        return String.format("Utworzono pokój nr: %d %s", this.number, this.bedType.toString());
    }
}
