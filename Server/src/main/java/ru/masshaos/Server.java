package ru.masshaos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    Logger logger = Logger.getInstance();

    public Server() {
        int idUser = 1;
        try (ServerSocket serverSocket = new ServerSocket(Settings.PORT)) {
            logger.log("Сервер запущен.");
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    UserThread userthread = new Userthread(socket, this, idUser++);
                } catch (IOException e) {
                    logger.log(Arrays.toString(e.getStackTrace()) + " " + e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.log(Arrays.toString(e.getStackTrace()) + " " + e.getMessage());
        }
        logger.log("Сервер остановлен");
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void sendMessageAll(Message msg) {
        users.forEach((key, value) -> value.getUserThread().sendMessage(msg));
    }
}
