package pl.javaCwiczenia2020.domain.room;

import pl.javaCwiczenia2020.domain.room.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final long id;
    private  int number;
    private  List<BedType> beds;

    Room(long id, int number, List<BedType> bedTypes) {
        this.id = id;
        this.number = number;
        if (bedTypes == null) {
            this.beds = new ArrayList<>();
        } else {
            this.beds = bedTypes;
        }
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < beds.size(); i++) {
            info.append("\n").append((i + 1)).append(" ").append(beds.get(i));
        }
        return String.format("%d Pokój nr: %d \nz %d łóżkami: ",
                this.id,
                this.number,
                this.beds.size()) + info;
    }

    public String toCSV() {

        String [] bedTypesStringArray = new String[this.beds.size()];

        int i = 0;
        for (BedType bedType : this.beds) {
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

    public long getId() {
        return this.id;
    }

    public RoomDTO convertToDTO(){

        StringBuilder bedList = new StringBuilder();

        int roomSize = 0;
        for (BedType bedType : this.beds) {
            bedList.append(bedType.toString()).append(",");
            roomSize += bedType.getSize();
        }

        return new RoomDTO(this.id, this.number, bedList.toString(), beds.size(), roomSize);

    }

    public int getNumber() {
        return this.number;
    }

    void addBed(BedType bedType) {
        this.beds.add(bedType);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBeds(List<BedType> beds) {
        this.beds = beds;
    }

    protected List<BedType> getBeds() {
        return this.beds;
    }
}
