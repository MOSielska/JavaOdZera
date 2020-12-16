package pl.javaCwiczenia2020.domain.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Properties {

    public static final String HOTEL_NAME = "Overloock";
    public static final int SYSTEM_VERSION = 1;
    public static final boolean IS_DEVELOPER_VERSION = true;

    public static final Path DIRECTORY_PATH = Paths.get(
            System.getProperty("user.home"),
            "reservation_system");

    public static void createDataDirectory() throws IOException {
        if(!Files.exists(DIRECTORY_PATH)) {
            Files.createDirectory(DIRECTORY_PATH);
        }
    }
}
