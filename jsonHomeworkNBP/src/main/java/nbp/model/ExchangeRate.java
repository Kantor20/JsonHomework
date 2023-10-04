package nbp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class ExchangeRate {
    private String No;
    private String effectiveDate;
    private double mid;
}
