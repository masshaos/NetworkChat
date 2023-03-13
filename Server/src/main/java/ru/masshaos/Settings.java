package ru.masshaos;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {

    private static final String SETTINGS_FILE_NAME = "Server/src/main/resources/server.properties";
    public static int PORT;
    public static String START_MESSAGE_NEW_CLIENT;
    public static String EXIT_MESSAGE;

    static {
        Logger logger = Logger.getInstance();
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader(SETTINGS_FILE_NAME)) {
            properties.load(fileReader);
            PORT = Integer.parseInt(properties.getProperty("PORT"));
            START_MESSAGE_NEW_CLIENT = properties.getProperty("START_MESSAGE_NEW_CLIENT");
            EXIT_MESSAGE = properties.getProperty("EXIT_MESSAGE");
            logger.log("Прочитаны настройки из файла " + SETTINGS_FILE_NAME);
            System.out.println("Прочитаны настройки из файла " + SETTINGS_FILE_NAME);
        } catch (IOException e) {
            System.out.println("Ошибка открытия файла " + SETTINGS_FILE_NAME);
            logger.log("Ошибка открытия файла " + SETTINGS_FILE_NAME);
            ;
        }
    }
}

