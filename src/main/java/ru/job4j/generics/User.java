package ru.job4j.generics;

public class User extends Base {
    private String surname;
    private String name;

    public User(String id, String surname, String name) {
        super(id);
        this.surname = surname;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
