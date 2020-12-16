package pl.javaCwiczenia2020;

import pl.javaCwiczenia2020.domain.util.Properties;
import pl.javaCwiczenia2020.exceptions.ReadWriteFileException;
import pl.javaCwiczenia2020.ui.text.TextUI;

import java.io.IOException;

public class App {

    private static final TextUI textUI = new TextUI();

    public static void main(String[] args) {

        try {
            Properties.createDataDirectory();
        } catch (IOException e) {
            throw new ReadWriteFileException(Properties.DIRECTORY_PATH.toString(), "create", "directory");
        }
        textUI.showSystemInfo();
        textUI.showMainMenu();
    }
}
