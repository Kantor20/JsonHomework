package pl.kurs.java.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class Car {
    private String name;
    private String model;
    private int capacity;
    private boolean turbo;
    private List<Equipment> equipment;
}
