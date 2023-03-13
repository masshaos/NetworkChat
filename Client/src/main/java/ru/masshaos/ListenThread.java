package ru.masshaos;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class ListenThread extends Thread {
    private final BufferedReader in;
    private final String userName;
    Logger logger = Logger.getInstance();

    public ListenThread(BufferedReader in, String userName) {
        this.in = in;
        this.userName = userName;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                String strMessage = in.readLine();
                Gson gson = new Gson();
                Message message = gson.fromJson(strMessage, Message.class);
                logger.log("{" + message.getName() + "} " + message.getText());
                if (!message.getName().equals(userName)){
                    System.out.println(message);
                }
            }
        }catch (IOException e){
            logger.log(Arrays.toString(e.getStackTrace())+" "+e.getMessage());
        }finally {
            logger.log("Принятие входящих сообщений от Сервера прекращено");
        }
    }
}
