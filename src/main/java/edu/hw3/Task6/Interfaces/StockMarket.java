package edu.hw3.Task6.Interfaces;

import edu.hw3.Task6.Abstract.Stock;

public interface StockMarket {
    void add(Stock stock);

    void remove(Stock stock);

    Stock mostValuableStock();
}
