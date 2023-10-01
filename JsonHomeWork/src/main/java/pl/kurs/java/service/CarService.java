package pl.kurs.java.service;

import pl.kurs.java.model.Car;
import pl.kurs.java.model.Equipment;

import java.util.*;

public class CarService {

    public Car findMostExpensiveCarByEquipment(List<Car> cars){
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


//    private double getEquipmentPrice1() {
//        double equipmentPrice = 0;
//        for (Equipment eq : car.getEquipment()) {
//            equipmentPrice += eq.getPrice();
//        }
//        return equipmentPrice;
//    }


//    public Car findMostExpensiveCarByEquipment(List<Car> cars) {
//        Car mostExpensiveCar = null;
//        double highestPrice = 0;
//
//        for (Car car : cars) {
//            double currentCarPrice = sumEquipmentPrice(car.getEquipment());
//            if (currentCarPrice > highestPrice) {
//                highestPrice = currentCarPrice;
//                mostExpensiveCar = car;
//            }
//        }
//
//        return mostExpensiveCar;
//    }
//
//    private double sumEquipmentPrice(List<Equipment> equipmentList) {
//        return equipmentList.stream()
//                .mapToDouble(Equipment::getPrice)
//                .sum();
//    }



}
