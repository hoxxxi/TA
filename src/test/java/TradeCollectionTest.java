import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TradeCollectionTest {

    @Test
    public void testLargestTradeCalculation() {
        TradeCollection collection = new TradeCollection();
        assertNull(collection.getLargestTrade(), "an empty trade collection should have no trades");

        Trade trade1 = new Trade(0,"TEST IT", 0.0, 10, "T");
        collection.insert(trade1);
        assertEquals(trade1, collection.getLargestTrade(), "single trade inserted in the collection should be the largest");

        Trade trade2 = new Trade(0,"TEST IT", 0.0, 100, "T");
        collection.insert(trade2);
        assertEquals(trade2, collection.getLargestTrade(), "unique largest trade should be the identified as largest");

        Trade trade3 = new Trade(0,"TEST IT", 0.0, 100, "T");
        collection.insert(trade3);
        assertEquals(trade3, collection.getLargestTrade(), "last inserted largest trade should be returned when we have multiple");

        assertEquals(trade3, collection.removeLast());
        assertEquals(trade2, collection.getLargestTrade());

        Trade trade4 = new Trade(0,"TEST IT", 0.0, 50, "T");
        collection.insert(trade4);
        collection.insert(trade3);

        assertEquals(trade3, collection.getLargestTrade());
        assertEquals(trade3, collection.removeLast());
        assertEquals(trade2, collection.getLargestTrade());
        assertEquals(trade4, collection.removeLast());
        assertEquals(trade2, collection.removeLast());
        assertEquals(trade1, collection.getLargestTrade());
    }

    @Test
    public void testAveragePriceCalculation() {
        TradeCollection collection = new TradeCollection();
        assertEquals(0.0d, collection.getAveragePrice(), 0.0, "an empty trade collection should zero average price");

        Trade trade1 = new Trade(0,"TEST IT", 0.15d, 10, "T");
        collection.insert(trade1);
        assertEquals(0.15d, collection.getAveragePrice(), 0.0, "the price of a single trade should be the average price");

        Trade trade2 = new Trade(0,"TEST IT", 0.11d, 100, "T");
        collection.insert(trade2);
        assertEquals(0.13d, collection.getAveragePrice(), 0.0, "correctly calculates the average price of 2 trades");

        Trade trade3 = new Trade(0,"TEST IT", 0.14d, 100, "T");
        collection.insert(trade3);
        assertEquals(0.1333d, collection.getAveragePrice(), 0.000034, "correctly calculates the average price of 3 trades");

        assertEquals(trade3, collection.removeLast());
        assertEquals(0.13d, collection.getAveragePrice(), 0.0, "correctly calculates the average price of 2 trades");
    }

    @Test
    public void testCountTradesWithFlagCalculation() {
        TradeCollection collection = new TradeCollection();
        assertEquals(0L, collection.countTradesWith("T"), "an empty trade collection should hold zero trades with any flag");

        Trade trade1 = new Trade(0,"TEST IT", 0.15d, 10, "T");
        collection.insert(trade1);
        assertEquals(1L, collection.countTradesWith("T"), "single trade inserted in the collection should be counted as 1");

        Trade trade2 = new Trade(0,"TEST IT", 0.11d, 100, "X");
        collection.insert(trade2);
        assertEquals(1L, collection.countTradesWith("T"), "filtering two trades should be able to correctly identify the one with flag T");

        Trade trade3 = new Trade(0,"TEST IT", 0.14d, 100, "T");
        collection.insert(trade3);
        assertEquals(2L, collection.countTradesWith("T"), "filtering multiple trades should be able to correctly identify two with flag T");

        assertEquals(trade3, collection.removeLast());
        assertEquals(1L, collection.countTradesWith("T"), "filtering two trades should be able to correctly identify the one with flag T");
    }
}
