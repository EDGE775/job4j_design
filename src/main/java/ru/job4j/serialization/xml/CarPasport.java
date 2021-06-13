package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "carPasport")
public class CarPasport {
    @XmlAttribute
    private String serialNumber;

    public CarPasport() {
    }

    public CarPasport(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "CarPasport{"
                + "serialNumber='" + serialNumber + '\''
                + '}';
    }
}
