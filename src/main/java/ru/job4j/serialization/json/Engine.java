package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.collection.List;

import java.util.ArrayList;

public class Engine {
    private final String model;

    public Engine(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "model='" + model + '\''
                + '}';
    }

    public static void main(String[] args) {

        JSONObject jsonEngine = new JSONObject("{\"model\":\"2JZ-GTE\"}");

        ArrayList<String> list = new ArrayList<>();
        list.add("Park assist");
        list.add("Cruise control");
        JSONArray jsonEquipments = new JSONArray(list);

        final Car car = new Car(false, 564646, new Engine("2JZ-GTE"),
                "Park assist", "Cruise control");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fourWheelDrive", car.isFourWheelDrive());
        jsonObject.put("serialNumber", car.getSerialNumber());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("equipment", jsonEquipments);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(car).toString());
    }

}