package edu.hw3.Task6.Binance;

import edu.hw3.Task6.Abstract.Stock;
import edu.hw3.Task6.Interfaces.StockMarket;
import java.util.PriorityQueue;

public class BinanceMarket implements StockMarket {

    private final PriorityQueue<Stock> marketList = new PriorityQueue<>();

    @Override
    public void add(Stock stock) {
        this.marketList.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        this.marketList.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return this.marketList.peek();
    }
}
