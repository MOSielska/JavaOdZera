package pl.javaCwiczenia2020.domain.room.dto;

public class RoomDTO {

    private int id;
    private int number;
    private String beds;
    private int bedCount;
    private  int roomSize;

    public RoomDTO(int id, int number, String beds, int bedCount, int roomSize) {
        this.id = id;
        this.number = number;
        this.beds = beds;
        this.bedCount = bedCount;
        this.roomSize = roomSize;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getBeds() {
        return beds;
    }

    public int getBedCount() {
        return bedCount;
    }

    public int getRoomSize() {
        return roomSize;
    }
}
