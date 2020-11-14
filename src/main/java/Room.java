public class Room {

    private int number;
    private int bedsNumber;
    private boolean isFree;

    public Room(int number, int bedsNumber) {
        this.number = number;
        this.bedsNumber = bedsNumber;
        this.isFree = true;
    }

    public String getInfo() {
        return String.format("Utworzono pokój nr: %d", this.number);
    }
}
