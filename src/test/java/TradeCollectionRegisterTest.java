import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeCollectionRegisterTest {

    TradeCollectionRegister register;

    @ParameterizedTest(name = "looking for XYZ LN trades with A flag in small data set")
    @CsvSource({"simpleInput.csv, XYZ LN, A"})
    public void testTradeHandlingLogicWithSmallDataSet(String file, String symbol, String flag) throws FileNotFoundException {
        loadFile(file);
        TradeCollection tradeCollectionXYZ = register.getTrades(symbol);
        assertEquals("2016-12-07 10:44:45.791,XYZ LN,200.0,1000,A", tradeCollectionXYZ.getLargestTrade().toString());
        assertEquals(200.005d, tradeCollectionXYZ.getAveragePrice());
        assertEquals(1L, tradeCollectionXYZ.countTradesWith(flag));
    }

    @ParameterizedTest(name = "looking for TEST IT trades with A flag in large data set")
    @CsvSource({"largeInput.csv, TEST IT, A"})
    public void testTradeHandlingLogicWithLargeDataSet(String file, String symbol, String flag) throws FileNotFoundException {
        loadFile(file);
        TradeCollection tradeCollectionTEST = register.getTrades(symbol);
        assertEquals("2016-12-07 12:06:26.032,TEST IT,9.35,9999,X", tradeCollectionTEST.getLargestTrade().toString());
        assertEquals(25.5393358597, tradeCollectionTEST.getAveragePrice(), 0.00000000005);
        assertEquals(1877L, tradeCollectionTEST.countTradesWith(flag));
    }

    public void loadFile(String file) throws FileNotFoundException {
        register = new TradeCollectionRegister();
        Scanner scanner = new Scanner(new File(getClass().getClassLoader().getResource(file).getFile()));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                String[] items = line.split(",");
                long timestamp = Long.parseLong(items[0]);
                String symbolInput = items[1];
                double price = Double.parseDouble(items[2]);
                long size = Long.parseLong(items[3]);
                String flags = items[4];
                register.handle(new Trade(timestamp, symbolInput, price, size, flags));
            } catch (NumberFormatException e) {
                System.err.println("Invalid trade message: " + line);
            }
        }
    }
}
