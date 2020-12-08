package pl.javaCwiczenia2020;

import pl.javaCwiczenia2020.ui.text.TextUI;

public class App {

    private static TextUI textUI = new TextUI();

    public static void main(String[] args) {

        textUI.showSystemInfo("Overlook", 1, true);
        textUI.showMainMenu();
    }
}
