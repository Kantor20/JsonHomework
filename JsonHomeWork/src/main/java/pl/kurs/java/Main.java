package pl.kurs.java;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.java.model.Car;
import pl.kurs.java.model.Equipment;
import pl.kurs.java.service.CarService;
import pl.kurs.java.service.EquipmentService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final String PATH = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CarService carService = new CarService();
        EquipmentService equipmentService = new EquipmentService();


        Car carAudi = new Car("Audi", "A4", 1200, true, List.of(new Equipment("audioBOSE", 1500.0), new Equipment("clima", 1900.0)));
        Car carBmw = new Car("Bmw", "750", 4400, false, List.of(new Equipment("iDrive", 15000.0), new Equipment("mPak", 5000.0)));
        Car carMercedes = new Car("Mercedes", "S-klasa", 5000, true, List.of(new Equipment("nightVision", 22000.0), new Equipment("amgPak", 4500.0)));
        Car maserati = Car.builder()
                .name("Maserati")
                .model("Quatroporte")
                .capacity(5.0)
                .turbo(false)
                .equipment(List.of(new Equipment("maseratiExhaust", 14000.0)))
                .build();

        mapper.writeValue(new File(PATH + "audiCar.json"), carAudi);
        mapper.writeValue(new File(PATH + "bmwCar.json"), carBmw);
        mapper.writeValue(new File(PATH + "mercedesCar.json"), carMercedes);

        Car loadedCarAudi = mapper.readValue(new File(PATH + "audiCar.json"), Car.class);
        Car loadedCarBmw = mapper.readValue(new File(PATH + "bmwCar.json"), Car.class);
        Car loadedCarMercedes = mapper.readValue(new File(PATH + "mercedesCar.json"), Car.class);

        List<Car> cars = List.of(loadedCarAudi, loadedCarBmw, loadedCarMercedes);
        
        Equipment mostExpensiveEquipment = equipmentService.findMostExpensiveEquipment(cars);
        Car expensiveCarByEquipment = carService.findMostExpensiveCarByEquipment(cars);
        List<Car> filteredCar = carService.filterCarsByCapacity(cars,1500,4600);
        double totalCostCar = carService.getTotalValueOfCars(cars);

        System.out.println(mostExpensiveEquipment);
        System.out.println(expensiveCarByEquipment);
        System.out.println(filteredCar);


    }
}
