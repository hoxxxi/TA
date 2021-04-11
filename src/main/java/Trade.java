import java.sql.Timestamp;

public class Trade {
    private final String symbol;
    private final Timestamp timestamp;
    private final double price;
    private final long qty;
    private final String flags;

    public Trade(long timestamp, String symbol, double price, long qty, String flags) {
        this.symbol = symbol;
        this.timestamp = new Timestamp(timestamp);
        this.price = price;
        this.qty = qty;
        this.flags = flags;
    }

    public String getSymbol() {
        return symbol;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public double getPrice() {
        return price;
    }

    public long getQty() {
        return qty;
    }

    public String getFlags() {
        return flags;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(timestamp.toString());
        builder.appendCodePoint(44);
        builder.append(symbol);
        builder.appendCodePoint(44);
        builder.append(price);
        builder.appendCodePoint(44);
        builder.append(qty);
        builder.appendCodePoint(44);
        builder.append(flags);
        return builder.toString();
    }
}