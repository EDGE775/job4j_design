package ru.job4j.serialization.json;

public class CarPasport {
    private final String serialNumber;

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
