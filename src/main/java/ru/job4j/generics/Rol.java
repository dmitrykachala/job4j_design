package ru.job4j.generics;

public class Rol extends Base {
    private String rolType;

    public Rol(String id, String rolType) {
        super(id);
        this.rolType = rolType;
    }

    public String getRolType() {
        return rolType;
    }

    public void setRolType(String rolType) {
        this.rolType = rolType;
    }
}
