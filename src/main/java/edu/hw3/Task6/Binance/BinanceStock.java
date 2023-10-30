package edu.hw3.Task6.Binance;

import edu.hw3.Task6.Abstract.Stock;
import org.jetbrains.annotations.NotNull;

public class BinanceStock extends Stock {
    public BinanceStock(String name, double price) {
        super(name, price);
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        var result = o.getPrice() - this.getPrice();
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
