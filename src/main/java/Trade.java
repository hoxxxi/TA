import java.sql.Timestamp;

public class Trade {
    private final String symbol;
    private final long timestamp;
    private final double price;
    private final long qty;
    private final String flags;

    public Trade(long timestamp, String symbol, double price, long qty, String flags) {
        this.symbol = symbol;
        this.timestamp = timestamp;
        this.price = price;
        this.qty = qty;
        this.flags = flags;
    }

    public String getSymbol() {
        return symbol;
    }

    public Timestamp getTimestamp() {
        return new Timestamp(timestamp);
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
        builder.append(getTimestamp());
        builder.appendCodePoint(44);
        builder.append(getSymbol());
        builder.appendCodePoint(44);
        builder.append(getPrice());
        builder.appendCodePoint(44);
        builder.append(getQty());
        builder.appendCodePoint(44);
        builder.append(getFlags());
        return builder.toString();
    }
}