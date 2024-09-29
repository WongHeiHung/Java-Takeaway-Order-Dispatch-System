package hk.ust.comp3021;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


public class DispatchSystem {

    /// The singleton you will use in the project.
    private static volatile DispatchSystem dispatchSystem;

    /// The field represents the current time stamp, we assume the current time stamp is 3600 seconds.
    private Long currentTimestamp = 3600L;

    /// The list stores the dishes parsed from the file.
    private List<Dish> availableDishes;

    /// The list stores the orders parsed from the file.
    private List<Order> availableOrders;

    /// The list stores the orders that is dispatched this time, and the orders should have a non-null rider field and calculated estimated time.
    private List<Order> dispatchedOrders;

    /// Task 1: Implement the constructor of the singleton pattern for the DispatchSystem class.
    /// Hint: Check if the dispatchSystem is null or not null, skip it when not null. Initialize the fields.

    private DispatchSystem() {
        if(dispatchSystem == null) {
            this.availableDishes = new ArrayList<Dish>();
            this.availableOrders = new ArrayList<Order>();
            this.dispatchedOrders = new ArrayList<Order>();
        }
        else {
            throw new IllegalStateException("Instance already created!");
        }
    }

    /// Task 1: Implement the getInstance() method for the singleton pattern.
    /// Hint: Check if the dispatchSystem is null or not null and create a new instance here.
    public static DispatchSystem getInstance() {
        if(dispatchSystem == null)
            dispatchSystem = new DispatchSystem();
        return dispatchSystem;
    }

    public Dish getDishById(Long id) {
        for(Dish dish : availableDishes){
            if(Objects.equals(dish.getId(), id))
                return dish;
        }
        return null;
    }

    public Boolean checkDishesInRestaurant(Restaurant restaurant, Long[] dishIds) {
        for (Long dishId : dishIds) {
            Dish dish = getDishById(dishId);
            if (dish == null || !dish.getRestaurantId().equals(restaurant.getId())) {
                return false;
            }
        }
        return true;
    }

    /// Task 2: Implement the parseAccounts() method to parse the accounts from the file.
    /// Hint: Do not forget to register the accounts into the static manager.
    public void parseAccounts(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            // Read the file and parse the accounts.
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length < 2) {
                    throw new IOException("The account file is not well formatted!");
                }

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                String accountType = fields[1];
                Long id = Long.valueOf(fields[0]);
                String name = fields[2];
                String contactNumber = fields[3];
                List<Double> location = Arrays.stream(fields[4].replaceAll("[\\[\\]]", "").trim()
                                .split("\\s+"))
                                .map(Double::parseDouble)
                                .toList();

                switch (accountType) {
                    case "CUSTOMER":
                        Integer CustomerType = Integer.valueOf(fields[5]);
                        String Gender = fields[6];
                        String Email = fields[7];
                        Customer newCustomer = new Customer(id,accountType,name,contactNumber,location,CustomerType,Gender,Email);
                        Account.getAccountManager().addCustomer(newCustomer);
                        break;
                    case "RESTAURANT":
                        String District = fields[5];
                        String Street = fields[6];
                        Restaurant newRestaurant = new Restaurant(id,accountType,name,contactNumber,location,District,Street);
                        Account.getAccountManager().addRestaurant(newRestaurant);
                        break;
                    case "RIDER":
                        String RIDER_Gender = fields[5];
                        Integer Status = Integer.valueOf(fields[6]);
                        Double UserRating = Double.valueOf(fields[7]);
                        Integer MonthTaskCount = Integer.valueOf(fields[8]);
                        Rider newRider = new Rider(id,accountType,name,contactNumber,location,RIDER_Gender,Status,UserRating,MonthTaskCount);
                        Account.getAccountManager().addRider(newRider);
                        break;
                    default:
                        continue;
                }

                // TODO.

            }
        }
    }

    /// Task 3: Implement the parseDishes() method to parse the dishes from the file.
    /// Hint: Do not forget to add the dishes to the corresponding restaurant and the availableDishes list.
    public void parseDishes(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            // Read the file and parse the dishes.
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                if (fields.length < 2) {
                    throw new IOException("The dish file is not well formatted!");
                }

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }
                Long id = Long.valueOf(fields[0]);
                String Name = fields[1];
                String Desc = fields[2];
                BigDecimal Price = new BigDecimal(fields[3]);
                Long RestaurantId = Long.valueOf(fields[4]);

                Dish newDish = new Dish(id,Name,Desc,Price,RestaurantId);
                Account.getAccountManager().getRestaurantById(RestaurantId).addDish(newDish);
                availableDishes.add(newDish);
                // TODO.

            }
        }
    }

    /// Task 4: Implement the parseOrders() method to parse the orders from the file.
    /// Hint: Do not forget to add the order to the availableOrders list and check if the dishes ordered are in the same restaurant, skip the record if not. You can use getDishById(), checkDishesInRestaurant(), and etc. here.
    public void parseOrders(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            // Read the file and parse the orders.
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                if (fields.length < 2) {
                    throw new IOException("The order file is not well formatted!");
                }

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                Long id = Long.valueOf(fields[0]);
                Integer status = Integer.valueOf(fields[1]);
                Long restaurantId = Long.valueOf(fields[2]);
                Restaurant restaurant = Account.getAccountManager().getRestaurantById(restaurantId);

                Long customerId = Long.valueOf(fields[3]);
                Customer customer = Account.getAccountManager().getCustomerById(customerId);

                Long CreateTime = Long.valueOf(fields[4]);
                Integer isPayed = Integer.valueOf(fields[5]);
                Boolean booleanIsPayed = isPayed != 0;

                List<Long> orderedDishes = Arrays.stream(fields[6].replaceAll("[\\[\\]]", "").trim()
                                .split("\\s+"))
                        .map(Long::parseLong)
                        .toList();

                List<Dish> dishes = new ArrayList<>();
                for (Long dishId : orderedDishes) {
                    Dish dish = getDishById(dishId);
                    if (dish != null) {
                        dishes.add(dish);
                    }
                }

                Long[] array = new Long[orderedDishes.size()];
                for (int i = 0; i < orderedDishes.size(); i++)
                    array[i] = orderedDishes.get(i);

                Rider rider;
                if(Objects.equals(fields[7], "NA")){
                    rider = null;
                }
                else {
                    Long riderId = Long.valueOf(fields[7]);
                    rider = Account.getAccountManager().getRiderById(riderId);
                }
                Order newOrder = new Order(id, status, restaurant, customer, CreateTime, booleanIsPayed, dishes, rider);
                if(checkDishesInRestaurant(restaurant,array))
                    availableOrders.add(newOrder);
                // TODO.

            }
        }
    }

    /// Task 5: Implement the getAvailablePendingOrders() method to get the available pending orders.
    /// Hint: The available pending orders should have the status of PENDING_ORDER, is payed, and the rider is null.
    public List<Order> getAvailablePendingOrders() {
        List<Order> availablePendingOrders = new ArrayList<>();
        for (Order order : availableOrders) {
            if (Objects.equals(order.getStatus(), Constants.PENDING_ORDER)) {
                availablePendingOrders.add(order);
            }
        }
        return availablePendingOrders;
    }

    /// Task 6: Implement the getRankedPendingOrders() method to rank the pending orders.
    /// Hint: Use the comparators you defined before, and sort the pending orders in order of the customer type (Top priority), order creation time (Second priority), and restaurant to customer distance (Least priority).
    public List<Order> getRankedPendingOrders(List<Order> pendingOrders) {
        List<Order> rankedOrders = new ArrayList<>(pendingOrders);

        for (int i = 0; i < rankedOrders.size(); i++) {
            for (int j = i + 1; j < rankedOrders.size(); j++) {
                if (CustomerPriorityRank.customerpriorityrank.compare(rankedOrders.get(i), rankedOrders.get(j)) == 0)
                    if (OrderCreateTimeRank.ordercreatetimerank.compare(rankedOrders.get(i), rankedOrders.get(j)) == 0)
                        if (RestaurantToCustomerDistanceRank.restauranttocustomerdistancerank.compare(rankedOrders.get(i), rankedOrders.get(j)) == 0){
                            Order temp = rankedOrders.get(i);
                            rankedOrders.set(i, rankedOrders.get(j));
                            rankedOrders.set(j, temp);
                        }
            }
        }
        return rankedOrders;
    }

    /// Task 7: Implement the getAvailableRiders() method to get the available riders to dispatch.
    /// Hint: The available riders should have the status of RIDER_ONLINE_ORDER.
    public List<Rider> getAvailableRiders() {
        List<Rider> availableRiders = new ArrayList<>();
        for (Rider rider : Account.getAccountManager().getRegisteredRiders()) {
            if (Objects.equals(rider.getStatus(), Constants.RIDER_ONLINE_ORDER)) {
                availableRiders.add(rider);
            }
        }
        return availableRiders;
    }

    /// Task 8: Implement the matchTheBestTask() method to choose the best rider for the order.
    /// Hint: The best rider should have the highest rank ranked in order of the distance between the rider and the restaurant (Top priority), the rider's user rating (Second priority), and the rider's month task count (Least priority).
    /// Use the comparators you defined before, you will also use the Task class here and the availableRiders here should be the currently available riders.
    public Task matchTheBestTask(Order order, List<Rider> availableRiders) {
        return null;
    }

    /// Task 9: Implement the dispatchFirstRound() method to dispatch the first round of orders.
    /// Hint: The strategy is that we assign the best rider to the orders ranked one by one until the orders or riders list is empty.
    /// Do not forget to 1. remove the dispatched rider every iteration, 2. change the status of the order and the rider after the order is dispatched, and 3. calculate the estimated time for the order.
    public void dispatchFirstRound() {
    }

    /// Do not modify the method. You should use the method to output orders for us to check the correctness of your implementation.
    public void writeOrders(String fileName, List<Order> orders) throws IOException {
        List<Order> orderedOrders = orders.stream().sorted(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).toList();

        // Write the dispatched orders to the file.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Order order : orderedOrders) {
                bufferedWriter.write(order.getId() + ", " + order.getStatus() + ", " + order.getRestaurant() + ", "
                        + order.getCustomer() + ", " + order.getCreateTime() + ", " + order.getIsPayed() + ", " +
                        order.getOrderedDishes() + ", " + order.getRider() + ", " + String.format("%.4f", order.getEstimatedTime()) + "\n");
            }
        }
    }

    /// Do not modify the method.
    public void writeAccounts(String fileName, List<Account> accounts) throws IOException {
        List<Account> orderedAccounts = accounts.stream().sorted(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).toList();

        // Write the dispatched orders to the file.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Account account : orderedAccounts) {
                bufferedWriter.write(account.toString() + "\n");
            }
        }
    }

    /// Do not modify the method.
    public void writeDishes(String fileName, List<Dish> dishes) throws IOException {
        List<Dish> orderedDishes = dishes.stream().sorted(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).toList();

        // Write the dispatched orders to the file.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Dish dish : orderedDishes) {
                bufferedWriter.write(dish.getId() + ", " + dish.getName() + ", " + dish.getDesc() + ", "
                        + dish.getPrice() + ", " + dish.getRestaurantId() + "\n");
            }
        }
    }

    /// Task 10: Implement the getTimeoutDispatchedOrders() method to get the timeout dispatched orders.
    /// Hint: Do not forget to take the current time stamp into consideration.
    public List<Order> getTimeoutDispatchedOrders() {
        return null;
    }

    /// Do not modify the method.
    public List<Order> getAvailableOrders() {
        return availableOrders;
    }

    /// Do not modify the method.
    public List<Order> getDispatchedOrders() {
        return dispatchedOrders;
    }

    /// Do not modify the method.
    public List<Account> getAccounts() {
        Account.AccountManager manager = Account.getAccountManager();
        return manager.getRegisteredAccounts();
    }

    /// Do not modify the method.
    public List<Dish> getDishes() {
        return availableDishes;
    }

    /// Finish the main method to test your implementation.a
    public static void main(String[] args) {
        try {
            DispatchSystem dispatchSystem = getInstance();
            dispatchSystem.parseAccounts("SampleInputAccounts.txt");
            dispatchSystem.parseDishes("SampleInputDishes.txt");
            dispatchSystem.parseOrders("SampleInputOrders.txt");
            dispatchSystem.writeOrders("availableOrders.txt", dispatchSystem.availableOrders);
/*
            dispatchSystem.dispatchFirstRound();

            dispatchSystem.writeOrders("firstRoundDispatchedOrders.txt", dispatchSystem.dispatchedOrders);
            List<Order> timeoutOrders = dispatchSystem.getTimeoutDispatchedOrders();

            dispatchSystem.writeOrders("timeoutDispatchedOrders.txt", timeoutOrders);
*/
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
