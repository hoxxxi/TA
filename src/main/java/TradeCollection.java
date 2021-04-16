import java.util.Stack;

public class TradeCollection {
    private double averagePrice;
    private final Stack<Trade> maxStack;
    private final Stack<Trade> tradeStack;

    /**
     * Constructor used to create an empty TradeCollection
     */
    public TradeCollection() {
        this.tradeStack = new Stack<>();
        this.maxStack = new Stack<>();
    }

    /**
     * Constructor used to create TradeCollection with one Trade
     */
    public TradeCollection(Trade trade) {
        this.tradeStack = new Stack<>();
        this.maxStack = new Stack<>();
        this.insert(trade);
    }

    /**
     * Largest trade and average trade price are calculated on insertion
     *
     * @param trade to be inserted in the collection
     */
    public void insert(Trade trade) {
        averagePrice = (averagePrice * tradeStack.size() + trade.getPrice()) / (double) (tradeStack.size()+1);
        tradeStack.push(trade);
        if(maxStack.empty() || trade.getQty() >= maxStack.peek().getQty()) {
            maxStack.push(trade);
        }
    }

    /**
     * @return last element in the LIFO queue
     */
    public Trade removeLast() {
        Trade trade = tradeStack.pop();
        averagePrice = tradeStack.empty() ? 0 : (((averagePrice * (tradeStack.size()+1)) - trade.getPrice()) / (double) (tradeStack.size()));
        if(!maxStack.empty() && trade==maxStack.peek()) {
            maxStack.pop();
        }
        return trade;
    }

    /**
     * @return largestTrade in collection, last inserted if there are multiple with the same size, null if empty
     */
    public Trade getLargestTrade(){
        if(maxStack.empty())
            return null;
        return maxStack.peek();
    }

    /**
     * @return average trade price, zero if empty
     */
    public double getAveragePrice(){
        return averagePrice;
    }

    /**
     * @param flag used to filter the trade collection
     * @return number of trades containing the flag parameter
     */
    public long countTradesWith(String flag){
        return tradeStack.stream().filter(s -> s.getFlags().contains(flag)).count();
    }
}
