package pl.kurs.java.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class Car {
    private String name;
    private String model;
    private double capacity;
    private boolean turbo;
    private List<Equipment> equipment;
}
