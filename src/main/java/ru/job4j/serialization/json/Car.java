package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final boolean fourWheelDrive;
    private final int serialNumber;
    private final Engine engine;
    private final String[] equipment;

    public Car(boolean fourWheelDrive, int serialNumber, Engine engine, String... equipment) {
        this.fourWheelDrive = fourWheelDrive;
        this.serialNumber = serialNumber;
        this.engine = engine;
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Car{"
                + "fourWheelDrive=" + fourWheelDrive
                + ", serialNumber=" + serialNumber
                + ", engine=" + engine
                + ", equipment=" + Arrays.toString(equipment)
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(false, 564646, new Engine("2JZ-GTE"),
                "Park assist", "Cruise control");

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
                "{"
                        + "\"fourWheelDrive\":true,"
                        + "\"serialNumber\":6785688,"
                        + "\"engine\":"
                        + "{"
                        + "\"model\":\"CAXA\""
                        + "},"
                        + "\"equipment\":"
                        + "[\"Light alloy wheel disks\",\"Alarm system\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}