package pl.kurs.java.service;

import pl.kurs.java.model.Car;
import pl.kurs.java.model.Equipment;

import java.util.Comparator;
import java.util.List;

public class EquipmentService {

    public Equipment findMostExpensiveEquipment(List<Car> cars){
        return cars.stream()
                .flatMap(car -> car.getEquipment().stream())
                .max(Comparator.comparingDouble(Equipment::getPrice))
                .orElse(null);
    }


//    public Equipment findMostExpensiveEquipment(List<Car> cars) {
//        Equipment mostExpensiveEquipment = null;
//        double highestPrice = 0;
//
//        for (Car car : cars) {
//            for (Equipment equipment : car.getEquipment()) {
//                if (equipment.getPrice() > highestPrice) {
//                    highestPrice = equipment.getPrice();
//                    mostExpensiveEquipment = equipment;
//                }
//            }
//        }
//
//        return mostExpensiveEquipment;
//    }


}
