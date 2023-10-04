package nbp.service;

import nbp.model.AverageCalculator;
import nbp.model.ExchangeRate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.OptionalDouble;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ExchangeAnalysisServiceTest {
    private ExchangeAnalysisService exchangeAnalysisService;
    private ExchangeRate rate1;
    private ExchangeRate rate2;
    private ExchangeRate rate3;

    @Mock
    private AverageCalculator averageCalculator;
    @Mock
    private CurrencyService currencyService;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        exchangeAnalysisService = new ExchangeAnalysisService(averageCalculator, currencyService);
        rate1 = new ExchangeRate("001", "2023-01-01", 1.0);
        rate2 = new ExchangeRate("002", "2023-01-15", 2.0);
        rate3 = new ExchangeRate("003", "2023-01-30", 1.5);
    }

    @Test
    public void testGetBetterInvestment() throws Exception {
        when(currencyService.getExchangeRates("EUR", "2023-01-01", "2023-01-31"))
                .thenReturn(Arrays.asList(rate1, rate2));
        when(currencyService.getExchangeRates("USD", "2023-01-01", "2023-01-31"))
                .thenReturn(Arrays.asList(rate1, rate3));

        String result = exchangeAnalysisService.getBetterInvestment("2023-01-01", "2023-01-31");
        assertEquals("EURO", result);
    }

    @Test
    public void testGetHighestRate() throws Exception {
        when(currencyService.getExchangeRates("EUR", "2023-01-01", "2023-01-31"))
                .thenReturn(Arrays.asList(rate1, rate2, rate3));

        OptionalDouble result = exchangeAnalysisService.getHighestRate("EUR", "2023-01-01", "2023-01-31");
        assertTrue(result.isPresent());
        assertEquals(2.0, result.getAsDouble(), 0.0001);
    }

    @Test
    public void testGetAverageGBP() throws Exception {
        when(currencyService.getExchangeRates("GBP", "2023-01-01", "2023-01-31"))
                .thenReturn(Arrays.asList(rate1, rate2, rate3));
        when(averageCalculator.calculateAverage(Arrays.asList(rate1, rate2, rate3)))
                .thenReturn(1.5);

        double result = exchangeAnalysisService.getAverageGBP("2023-01-01", "2023-01-31");
        assertEquals(1.5, result, 0.0001);
    }

    @Test
    public void testGetMaxAvgGBP() throws Exception {
        when(currencyService.getExchangeRates("GBP", "2023-01-01", "2023-01-31"))
                .thenReturn(Arrays.asList(rate1, rate2, rate3));

        OptionalDouble result = exchangeAnalysisService.getMaxAvgGBP("2023-01-01", "2023-01-31");
        assertTrue(result.isPresent());
        assertEquals(2.0, result.getAsDouble(), 0.0001);
    }

    @Test
    public void testGetMinAvgGBP() throws Exception {
        when(currencyService.getExchangeRates("GBP", "2023-01-01", "2023-01-31"))
                .thenReturn(Arrays.asList(rate1, rate2, rate3));

        OptionalDouble result = exchangeAnalysisService.getMinAvgGBP("2023-01-01", "2023-01-31");
        assertTrue(result.isPresent());
        assertEquals(1.0, result.getAsDouble(), 0.0001);
    }
}