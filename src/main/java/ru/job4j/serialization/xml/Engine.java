package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
public class Engine {

    @XmlAttribute
    private String model;

    public Engine() {

    }

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
