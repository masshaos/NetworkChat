package ru.masshaos;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println("PORT: " + Settings.PORT);
        System.out.println("START MESS: " + Settings.START_MESSAGE_NEW_CLIENT);
        System.out.println("EXIT MESS: " + Settings.EXIT_MESSAGE);
        if (args.length == 1) Settings.SERVER_NAME = args[0];
        System.out.println("SERVER NAME: " + Settings.SERVER_NAME);

        new Client();
    }
}