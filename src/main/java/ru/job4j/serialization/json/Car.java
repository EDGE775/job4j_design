package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.util.Arrays;

public class Car {
    private final String model;
    private final boolean roofRack;
    private final int maxSpeed;
    private final CarPasport carPasport;
    private final String[] details;

    public Car(String model, boolean roofRack,
               int maxSpeed, CarPasport carPasport,
               String[] details) {
        this.model = model;
        this.roofRack = roofRack;
        this.maxSpeed = maxSpeed;
        this.carPasport = carPasport;
        this.details = details;
    }

    public String getModel() {
        return model;
    }

    public boolean isRoofRack() {
        return roofRack;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public CarPasport getCarPasport() {
        return carPasport;
    }

    public String[] getDetails() {
        return details;
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

    public static void main(String[] args) {
        Car car = new Car("Ford", false, 140,
                new CarPasport("123456789"), new String[]{"Engine", "Wheel"});

        //      Создание json с помощью библиотеки Gson и обратно
        Gson gson = new GsonBuilder().create();
        String jsonCar = gson.toJson(car);
        System.out.println(jsonCar);
        Car carFromJson = gson.fromJson(jsonCar, Car.class);
        System.out.println(carFromJson);
        System.out.println();

        //        Создание json с помощью библиотеки org.json
        JSONObject jsonObject = new JSONObject(car);
        System.out.println(jsonObject);
    }
}
