package hk.ust.comp3021;

import hk.ust.comp3021.rank.PendingOrderRank;

public class RestaurantToCustomerDistanceRank implements PendingOrderRank {
    public static RestaurantToCustomerDistanceRank restauranttocustomerdistancerank = new RestaurantToCustomerDistanceRank();

    private double calculateDistance(Order order) {
        // Assuming Order has methods to get restaurant and customer locations
        Location restaurantLocation = order.getRestaurant().location;
        Location customerLocation = order.getCustomer().location;

        return restaurantLocation.distanceTo(customerLocation);
    }
    @Override
    public int compare(Order source, Order target) {

        if(calculateDistance(source) > calculateDistance(target))
            return 1;
        else
            return 0;

    }
}