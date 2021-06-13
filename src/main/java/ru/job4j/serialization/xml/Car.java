package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private String model;

    @XmlAttribute
    private boolean roofRack;

    @XmlAttribute
    private int maxSpeed;

    private CarPasport carPasport;

    @XmlElementWrapper(name = "details")
    @XmlElement(name = "detail")
    private String[] details;

    public Car() {
    }

    public Car(String model, boolean roofRack,
               int maxSpeed, CarPasport carPasport,
               String[] details) {
        this.model = model;
        this.roofRack = roofRack;
        this.maxSpeed = maxSpeed;
        this.carPasport = carPasport;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + ", roofRack=" + roofRack
                + ", maxSpeed=" + maxSpeed
                + ", carPasport=" + carPasport
                + ", details=" + Arrays.toString(details)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        Car car = new Car("Ford", false, 140,
                new CarPasport("123456789"), new String[]{"Engine", "Wheel"});

        JAXBContext context = JAXBContext.newInstance(Car.class, CarPasport.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                    new FileOutputStream("./src/main/resources/car.xml")))) {
                out.write(writer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("./src/main/resources/car.xml"))) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
