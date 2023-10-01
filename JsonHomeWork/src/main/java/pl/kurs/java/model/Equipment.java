package pl.kurs.java.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Equipment {
    private String name;
    private double price;

}
