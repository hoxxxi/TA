import java.util.ArrayList;

public class TradeCollection {
    private Trade largestTrade;
    private double averagePrice;
    private final ArrayList<Trade> tradeList;

    /**
     * Constructor used to create an empty TradeCollection
     */
    public TradeCollection() {
        this.tradeList = new ArrayList<>();
    }

    /**
     * Constructor used to create TradeCollection with one Trade
     */
    public TradeCollection(Trade trade) {
        this.tradeList = new ArrayList<>();
        this.insert(trade);
    }

    /*
     * Largest trade and average trade price are calculated on insertion
     *
     * @param trade to be inserted in the collection
     */
    public void insert(Trade trade) {
        largestTrade = largestTrade != null && largestTrade.getQty() > trade.getQty() ? largestTrade : trade;
        averagePrice = (averagePrice * tradeList.size() + trade.getPrice())/(tradeList.size()+1);
        tradeList.add(trade);
    }

    /**
     * @return largestTrade in collection, last inserted if there are multiple with the same size, null if empty
     */
    public Trade getLargestTrade(){
        return largestTrade;
    }

    /**
     * @return average trade price, zero if empty
     */
    public double getAveragePrice(){
        return averagePrice;
    }

    /*
     * @param flag used to filter the trade collection
     * @return number of trades containing the flag parameter
     */
    public long countTradesWith(String flag){
        return tradeList.stream().filter(s -> s.getFlags().contains(flag)).count();
    }
}
