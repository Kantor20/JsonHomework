package pl.kurs.java.service;

import pl.kurs.java.model.Car;
import pl.kurs.java.model.Equipment;

import java.util.*;
import java.util.stream.Collectors;

public class CarService {

    public Car findMostExpensiveCarByEquipment(List<Car> cars) {
        return Optional.ofNullable(cars)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingDouble(this::getEquipmentPrice))
                .orElse(null);
    }

    private double getEquipmentPrice(Car car) {
        return car.getEquipment()
                .stream()
                .mapToDouble(Equipment::getPrice)
                .sum();
    }

    public List<Car> filterCarsByCapacity(List<Car> cars, double minCapacity, double maxCapacity) {
        return Optional.ofNullable(cars)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(car -> car.getCapacity() >= minCapacity && car.getCapacity() <= maxCapacity)
                .collect(Collectors.toList());
    }

    public double getTotalValueOfCars(List<Car> cars) {
        return Optional.ofNullable(cars)
                .orElseGet(Collections::emptyList)
                .stream()
                .mapToDouble(car -> car.getEquipment().stream().mapToDouble(Equipment::getPrice).sum())
                .sum();
    }

    public List<Car> searchCarsByBrand(List<Car> cars, String brand) {
        return Optional.ofNullable(cars)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(car -> car.getName().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public List<Car> searchCarsByModel(List<Car> cars, String model) {
        return cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }
    public double averageEquipmentPrice(List<Car> cars) {
        return Optional.ofNullable(cars)
                .orElseGet(Collections::emptyList)
                .stream()
                .flatMap(car -> car.getEquipment().stream())
                .mapToDouble(Equipment::getPrice)
                .average()
                .orElse(0.0);
    }
}
