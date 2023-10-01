package pl.kurs.java.service;

import pl.kurs.java.model.Car;
import pl.kurs.java.model.Equipment;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EquipmentService {

    public Equipment findMostExpensiveEquipment(List<Car> cars){
        return Optional.ofNullable(cars)
                .orElseGet(Collections::emptyList)
                .stream()
                .flatMap(car -> car.getEquipment().stream())
                .max(Comparator.comparingDouble(Equipment::getPrice))
                .orElse(null);
    }

    public Equipment findCheapestEquipment(List<Car> cars) {
        return Optional.ofNullable(cars)
                .orElseGet(Collections::emptyList)
                .stream()
                .flatMap(car -> car.getEquipment().stream())
                .min(Comparator.comparingDouble(Equipment::getPrice))
                .orElse(null);
    }

    public List<Equipment> searchEquipmentByName(List<Car> cars, String equipmentName) {
        return Optional.ofNullable(cars)
                .orElseGet(Collections::emptyList)
                .stream()
                .flatMap(car -> car.getEquipment().stream())
                .filter(equipment -> equipment.getName().equalsIgnoreCase(equipmentName))
                .collect(Collectors.toList());
    }

    public List<Equipment> filterEquipmentByPrice(List<Car> cars, double minPrice, double maxPrice) {
        return Optional.ofNullable(cars)
                .orElseGet(Collections::emptyList)
                .stream()
                .flatMap(car -> car.getEquipment().stream())
                .filter(equipment -> equipment.getPrice() >= minPrice && equipment.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

}
