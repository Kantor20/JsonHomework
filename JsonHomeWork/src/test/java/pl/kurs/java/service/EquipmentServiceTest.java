package pl.kurs.java.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.kurs.java.model.Car;
import pl.kurs.java.model.Equipment;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EquipmentServiceTest {
    private static final String PATH = "src/main/resources/";

    private Car car;
    private Car car2;
    private Car car3;
    private EquipmentService equipmentService;
    private List<Car> cars;

    @Before
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        car = mapper.readValue(new File(PATH + "audiCar.json"), Car.class);
        car2 = mapper.readValue(new File(PATH + "bmwCar.json"), Car.class);
        car3 = mapper.readValue(new File(PATH + "mercedesCar.json"), Car.class);
        equipmentService = new EquipmentService();
        cars = List.of(car3,car2,car);
    }

    @Test
    public void shouldReturn(){
        Equipment equipment = new Equipment("nightVision", 22000.0);

        Assert.assertEquals(equipment, equipmentService.findMostExpensiveEquipment(cars));
    }

}