package ru.job4j.serialization.json;

public class Engine {
    private final String model;

    public Engine(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "model='" + model + '\''
                + '}';
    }
}
