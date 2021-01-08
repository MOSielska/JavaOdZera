package pl.javaCwiczenia2020.domain.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class Properties {

    public static final String HOTEL_NAME = "Overloock";
    public static final int SYSTEM_VERSION = 1;
    public static final boolean IS_DEVELOPER_VERSION = true;
    public static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int HOTEL_NIGHT_START_HOUR = 15;
    public static final int HOTEL_NIGHT_START_MINUTE = 0;
    public static final int HOTEL_NIGHT_END_HOUR = 10;
    public static final int HOTEL_NIGHT_END_MINUTE = 0;

    public static final String SINGLE_BED = "SINGLE";
    public static final String DOBBLE_BED = "DOBBLE";
    public static final String KING_SIZE_BED = "KING_SIZE";

    public static final String FEMALE = "Żeńska";
    public static final  String MALE = "Męska";


    public static final Path DIRECTORY_PATH = Paths.get(
            System.getProperty("user.home"),
            "reservation_system");

    public static void createDataDirectory() throws IOException {
        if(!Files.exists(DIRECTORY_PATH)) {
            Files.createDirectory(DIRECTORY_PATH);
        }
    }
}
