package ru.masshaos;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println("PORT: " + Settings.PORT);
        System.out.println("START MESS: " + Settings.START_MESSAGE_NEW_CLIENT);
        System.out.println("EXIT MES: " + Settings.EXIT_MESSAGE);

//        Logger logger = Logger.getInstance();
//        logger.log("Kzkzkz");
//        logger.log(";lkjhbgvcf");

        Server server = new Server();

    }

}