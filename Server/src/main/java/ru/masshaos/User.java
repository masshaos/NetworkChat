package ru.masshaos;

public class User {
    private final int id;
    private final String name;
    private final UserThread userThread;

    public User(int id, String name, UserThread userThread) {
        this.id = id;
        this.name = name;
        this.userThread = userThread;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserThread getUserThread() {
        return userThread;
    }
}
