package hk.ust.comp3021;

import hk.ust.comp3021.rank.PendingOrderRank;

public class OrderCreateTimeRank implements PendingOrderRank {
    @Override
    public int compare(Order source, Order target) {
        return 0;
    }
}