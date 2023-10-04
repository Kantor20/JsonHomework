package nbp;


import nbp.model.AverageCalculator;
import nbp.model.ExchangeRate;
import nbp.service.AverageExchangeCalculator;
import nbp.service.CurrencyService;
import nbp.service.ExchangeAnalysisService;

import java.util.List;
import java.util.OptionalDouble;

public class Main {

    private final CurrencyService service = CurrencyService.getInstance();

    public static void main(String[] args) throws Exception {
        CurrencyService cs = CurrencyService.getInstance();
        String currencyCode = "usd";
        String startDate = "2023-01-02";
        String endDate = "2023-01-31";
        String filePath = "exchangeRates.json";


        List<ExchangeRate> exchangeRateList = cs.getExchangeRates("USD", "2023-01-01", "2023-01-31");

        System.out.println("LISTA: " + exchangeRateList);
        AverageCalculator calc = new AverageExchangeCalculator();
        double average = calc.calculateAverage(exchangeRateList);
        System.out.println("SREDNIA: " + average);

        ExchangeAnalysisService exchangeAnalysis = new ExchangeAnalysisService(calc,cs);

        String betterInvestment = exchangeAnalysis.getBetterInvestment("2023-02-01", "2023-02-13");
        OptionalDouble highestRateForEUR = exchangeAnalysis.getHighestRate("EUR","2023-01-01", "2023-01-31");
        OptionalDouble highestRateForUSD = exchangeAnalysis.getHighestRate("USD","2023-01-01", "2023-01-31");

        System.out.println("Better investment: " + betterInvestment);
        System.out.println("Highest rate for EUR: " + highestRateForEUR);
        System.out.println("Highest rate for USD: " + highestRateForUSD);

        double averageGBP = exchangeAnalysis.getAverageGBP("2023-02-01", "2023-02-13");
        OptionalDouble maxAvgGBP = exchangeAnalysis.getMaxAvgGBP("2023-02-01", "2023-02-13");
        OptionalDouble minAvgGBP = exchangeAnalysis.getMinAvgGBP("2023-02-01", "2023-02-13");

        System.out.println("AverageGBP: " + averageGBP);
        System.out.println("Max average GBP: " + maxAvgGBP);
        System.out.println("Min average GBP: " + minAvgGBP);
    }
}












//package nbp;
//
//import nbp.model.AverageCalculator;
//import nbp.model.ExchangeRate;
//import nbp.service.AverageExchangeCalculator;
//import nbp.service.CurrencyService;
//import org.dom4j.DocumentHelper;
//
//import java.util.List;
//
//
//public class Main {
//    public static void main(String[] args) throws Exception {
//        CurrencyService cs = CurrencyService.getInstance();
//        String currencyCode = "usd";
//        String startDate = "2023-01-02";
//        String endDate = "2023-01-31";
//        String filePath = "exchangeRates.json";
//
//
//        List<ExchangeRate> exchangeRateList = cs.getExchangeRates("USD", "2023-01-01", "2023-01-31");
//
//        System.out.println("LISTA: " + exchangeRateList);
//        AverageCalculator calc = new AverageExchangeCalculator();
//        double average = calc.calculateAverage(exchangeRateList);
//        System.out.println("SREDNIA: " + average);
//
//        //System.out.println("Dane zostały zapisane do pliku " + filePath);
//
//    }
//}
