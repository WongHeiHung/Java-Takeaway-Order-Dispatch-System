package hk.ust.comp3021;

import hk.ust.comp3021.rank.TaskRank;

public class RiderToRestaurantRank implements TaskRank {
    public static RiderToRestaurantRank riderToRestaurantRank = new RiderToRestaurantRank();
    private double calculateDistance(Task task) {
        // Assuming Order has methods to get restaurant and customer locations
        Location restaurantLocation = task.getOrder().getRestaurant().getLocation();
        Location riderLocation = task.getRider().getLocation();

        return restaurantLocation.distanceTo(riderLocation);
    }
    @Override
    public int compare(Task source, Task target) {
        if(calculateDistance(source) == calculateDistance(target))
            return 0;
        if(calculateDistance(source) < calculateDistance(target))
            return 1;
        else
            return -1;

    }
}

