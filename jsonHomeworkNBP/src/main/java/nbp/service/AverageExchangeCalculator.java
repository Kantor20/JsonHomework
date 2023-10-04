package nbp.service;

import nbp.model.AverageCalculator;
import nbp.model.ExchangeRate;

import java.util.List;

public class AverageExchangeCalculator implements AverageCalculator {


    @Override
    public double calculateAverage(List<ExchangeRate> exchangeRates) {
        double sum = 0.0;

        for (ExchangeRate rate : exchangeRates) {
            sum += rate.getMid();
        }

        return sum / exchangeRates.size();
    }
}
