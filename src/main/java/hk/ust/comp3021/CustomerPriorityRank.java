package hk.ust.comp3021;

import hk.ust.comp3021.rank.PendingOrderRank;

public class CustomerPriorityRank implements PendingOrderRank{
    public static CustomerPriorityRank customerpriorityrank = new CustomerPriorityRank();
    @Override
    public int compare(Order source, Order target) {
        if(source.getCustomer().getCustomerType() < target.getCustomer().getCustomerType())
            return 1;
        else
            return 0;

    }
}
