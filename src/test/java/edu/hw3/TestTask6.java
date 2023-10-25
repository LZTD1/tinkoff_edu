package edu.hw3;

import edu.hw3.Task6.Abstract.Stock;
import edu.hw3.Task6.Binance.BinanceMarket;
import edu.hw3.Task6.Binance.BinanceStock;
import edu.hw3.Task6.Interfaces.StockMarket;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask6 {

    @Test
    void getMostValuableStock(){
        StockMarket binance = new BinanceMarket();

        Stock huaweiStockInBinance = new BinanceStock("Huawei", 4);
        Stock appleStockInBinance = new BinanceStock("Apple", 5);
        Stock samsungStockInBinance = new BinanceStock("Samsung", 3);

        binance.add(huaweiStockInBinance);
        binance.add(appleStockInBinance);
        binance.add(samsungStockInBinance);

        var result = binance.mostValuableStock();
        System.out.println(result.getName());
        assertThat(result).isEqualTo(appleStockInBinance);
    }
    @Test
    void getMostValuableStock_2(){
        StockMarket binance = new BinanceMarket();

        Stock huaweiStockInBinance = new BinanceStock("Huawei", 1.998);
        Stock appleStockInBinance = new BinanceStock("Apple", 1.999);
        Stock samsungStockInBinance = new BinanceStock("Samsung", 1.997);

        binance.add(huaweiStockInBinance);
        binance.add(appleStockInBinance);
        binance.add(samsungStockInBinance);

        var result = binance.mostValuableStock();
        System.out.println(result.getName());
        assertThat(result).isEqualTo(appleStockInBinance);
    }
}
