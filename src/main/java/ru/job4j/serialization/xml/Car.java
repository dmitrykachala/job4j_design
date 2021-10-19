package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean fourWheelDrive;

    @XmlAttribute
    private int serialNumber;

    private Engine engine;

    @XmlElementWrapper(name = "equipments")
    @XmlElement(name = "equipment")
    private String[] equipment;

    public Car(boolean fourWheelDrive, int serialNumber, Engine engine, String... equipment) {
        this.fourWheelDrive = fourWheelDrive;
        this.serialNumber = serialNumber;
        this.engine = engine;
        this.equipment = equipment;
    }

    public Car() {

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

    public static void main(String[] args) throws JAXBException {

        final Car car = new Car(false, 3535, new Engine("SR20-DET"),
                "Manual gearshift", "2 doors", "380hp");

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }

}
