package ru.masshaos;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;

public class UserThread extends Thread {
    private final Socket socket;
    private final Server server;
    private BufferedReader in;
    private PrintWriter out;
    private final int idUser;
    Logger logger = Logger.getInstance();

    public UserThread(Socket socket, Server server, int idUser) {
        this.socket = socket;
        this.server = server;
        this.idUser = idUser;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            logger.log(Arrays.toString(e.getStackTrace()) + " " + e.getMessage());
        }
    }
    @Override
    public void run(){
        try {
            System.out.println("Клиент " + idUser + " подключен.");
            logger.log("Клиент " + idUser + " подключен:" + socket);
            String line;
            while ((line = in.readLine()) != null){
                Gson gson = new Gson();
                Message message = gson.fromJson(line, Message.class);
                if (message.getText().equals(Settings.START_MESSAGE_NEW_CLIENT)){
                    logger.log("Юзер \"" + message.getName() + "\" вошел в чат");
                    System.out.println("Юзер \"" + message.getName() + "\" вошел в чат");
                    server.getUsers().put(idUser, new User(idUser, message.getName(), this));
                    server.sendMessageAll(new Message(
                            "Server","\"" + message.getName()+"\" вошел в чат", new Date()));
                    continue;
                }
                if (message.getText().equals(Settings.EXIT_MESSAGE)){
                    User user = server.getUsers().get(idUser);
                    logger.log("Юзер \"" + user.getName()+"\" покинул чат");
                    server.sendMessageAll(new Message (
                            "Server", "\"" + user.getName() + "\" покинул чат", new Date()));
                    server.getUsers().remove(idUser);
                    break;
                }
                logger.log("{" + message.getName() + "}" + message.getText());
                server.sendMessageAll(message);
            }
        } catch (IOException e) {
            logger.log(Arrays.toString(e.getStackTrace()) + " " + e.getMessage());
        } finally{
            try{
                socket.close();
                logger.log("Клиент"+ idUser + " отключен:" + socket);
                }catch (IOException e) {
                logger.log(Arrays.toString(e.getStackTrace()) + " " + e.getMessage());
            }
        }
    }

    public void sendMessage( Message message) {
        try{
            Gson gson = new Gson();
            String jsonMessage = gson.toJson(message);
            System.out.println("{" + message.getName() + "} " + message.getText());
            out.println(jsonMessage);
            } catch (Exception e) {
            logger.log(Arrays.toString(e.getStackTrace()) + " " + e.getMessage());
        }
    }
}
