package hk.ust.comp3021;

import hk.ust.comp3021.rank.PendingOrderRank;

import java.util.Objects;

public class CustomerPriorityRank implements PendingOrderRank{
    public static CustomerPriorityRank customerpriorityrank = new CustomerPriorityRank();
    @Override
    public int compare(Order source, Order target) {
        if(Objects.equals(source.getCustomer().getCustomerType(), target.getCustomer().getCustomerType()))
            return 0;
        else if (source.getCustomer().getCustomerType() < target.getCustomer().getCustomerType())
            return 1;
        else
            return -1;
    }
}
