package pl.javaCwiczenia2020.domain.room;

import pl.javaCwiczenia2020.domain.util.SystemUtils;

public enum BedType {
    SINGLE(1, SystemUtils.SINGLE_BED),
    DOBBLE(2, SystemUtils.DOBBLE_BED),
    KING_SIZE(2, SystemUtils.KING_SIZE_BED);

    private int size;
    private String asStr;

    BedType(int size, String asStr) {
        this.size = size;
        this.asStr = asStr;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return this.asStr;
    }
}
