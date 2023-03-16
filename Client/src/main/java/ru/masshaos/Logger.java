package ru.masshaos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    int count;
    private static Logger INSTANCE;

    private Logger() {
    }

    public static synchronized Logger getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Logger();
        }
        return INSTANCE;
    }

    void log(String msg){
        try (FileWriter fileWriter = new FileWriter("log_client.log", true)){
            fileWriter.write(++count + ". [" + new Date() + "] "+msg + "\n");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
