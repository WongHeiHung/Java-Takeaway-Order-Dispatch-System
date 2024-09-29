package hk.ust.comp3021;

import hk.ust.comp3021.rank.PendingOrderRank;

public class OrderCreateTimeRank implements PendingOrderRank {
    public static OrderCreateTimeRank ordercreatetimerank = new OrderCreateTimeRank();
    @Override
    public int compare(Order source, Order target) {
        if(source.getCreateTime() < target.getCreateTime())
            return 1;
        else
            return 0;
    }
}