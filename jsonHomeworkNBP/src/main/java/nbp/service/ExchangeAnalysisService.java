package nbp.service;

import nbp.model.AverageCalculator;
import nbp.model.ExchangeRate;

import java.util.List;
import java.util.OptionalDouble;

public class ExchangeAnalysisService {

    private final AverageCalculator averageCalculator;
    private CurrencyService service;

    public ExchangeAnalysisService(AverageCalculator averageCalculator, CurrencyService currencyService){
        this.averageCalculator = averageCalculator;
        this.service = currencyService;
    }

    public String getBetterInvestment(String startDate, String stopDate) throws Exception {
        List<ExchangeRate> euroRates = service.getExchangeRates("EUR", startDate, stopDate);
        List<ExchangeRate> usdRates = service.getExchangeRates("USD", startDate, stopDate);

        double euroChange = (euroRates.get(euroRates.size() - 1).getMid() / euroRates.get(0).getMid()) - 1;
        double usdChange = (usdRates.get(usdRates.size() - 1).getMid() / usdRates.get(0).getMid()) - 1;

        return euroChange > usdChange ? "EURO" : "USD";
    }

    public OptionalDouble getHighestRate(String currencyCode, String startDate, String endDate) throws Exception {
        List<ExchangeRate> rates = service.getExchangeRates(currencyCode, startDate, endDate);
        return rates.stream().mapToDouble(ExchangeRate::getMid).max();
    }

    public double getAverageGBP(String startDate, String endDate) throws Exception {
        List<ExchangeRate> rates = service.getExchangeRates("GBP", startDate, endDate);
        return averageCalculator.calculateAverage(rates);
    }
    public OptionalDouble getMaxAvgGBP(String startDate, String endDate) throws Exception {
        List<ExchangeRate> rates = service.getExchangeRates("GBP", startDate, endDate);
        return rates.stream().mapToDouble(ExchangeRate::getMid).max();
    }
    public OptionalDouble getMinAvgGBP(String startDate, String endDate) throws Exception {
        List<ExchangeRate> rates = service.getExchangeRates("GBP", startDate, endDate);
        return rates.stream().mapToDouble(ExchangeRate::getMid).min();
    }
}
