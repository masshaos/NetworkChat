package ru.masshaos;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings
{
    private static final String SETTINGS_FILE_NAME = "Client/src/main/resources/client.properties";
    public static int PORT;
    public static String START_MESSAGE_NEW_CLIENT;
    public static String EXIT_MESSAGE;
    public static String SERVER_NAME;

    static{
        Properties properties=new Properties();
        try (FileReader fileReader =  new FileReader(SETTINGS_FILE_NAME)){
            properties.load(fileReader);
            PORT = Integer.parseInt(properties.getProperty("PORT"));
            START_MESSAGE_NEW_CLIENT = properties.getProperty("START_MESSAGE_NEW_CLIENT");
            SERVER_NAME = properties.getProperty("SERVER_NAME");
            //todo logger
        }catch (IOException e){
            //todo logger
        }
    }
}
