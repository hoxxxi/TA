import java.util.HashMap;
import java.util.Map;

public class TradeCollectionRegister {
    private Map<String, TradeCollection> tradeMap = new HashMap<>();

    /**
     * @param trade to be added to the trade register
     */
    public void handle(Trade trade) {
        if(tradeMap.containsKey(trade.getSymbol())) {
            tradeMap.get(trade.getSymbol()).insert(trade);
        } else {
            tradeMap.put(trade.getSymbol(), new TradeCollection(trade));
        }
    }

    /**
     * @param symbol representing the trading instrument we are looking for
     * @return TradeCollection of all trades previously handled by the trade register
     */
    public TradeCollection getTrades(String symbol) {
        return tradeMap.get(symbol);
    }
}