package hk.ust.comp3021;

import hk.ust.comp3021.rank.PendingOrderRank;

public class CustomerPriorityRank implements PendingOrderRank{
    @Override
    public int compare(Order source, Order target) {
        return 0;
    }
}
