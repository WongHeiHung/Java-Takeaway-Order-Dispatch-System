package hk.ust.comp3021;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    protected Long id;

    protected String accountType;

    protected String name;

    protected String contactNumber;

    protected Location location;

    public Account(Long id, String accountType, String name, String contactNumber, List<Double> location) {
        this.id = id;
        this.accountType = accountType;
        this.name = name;
        this.contactNumber = contactNumber;
        this.location = new Location(location.get(0),location.get(1));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    /// This is where the registered accounts are stored.
    protected static class AccountManager {

        private List<Account> registeredAccounts;

        private List<Customer> registeredCustomers;

        private List<Restaurant> registeredRestaurants;

        private List<Rider> registeredRiders;

        public AccountManager() {
        }

        /// Do not modify this method.
        public List<Account> getRegisteredAccounts() {
            return registeredAccounts;
        }

        public List<Customer> getRegisteredCustomers() {
            return registeredCustomers;
        }

        public List<Restaurant> getRegisteredRestaurants() {
            return registeredRestaurants;
        }

        public List<Rider> getRegisteredRiders() {
            return registeredRiders;
        }

        public Account getAccountById(Long id) {
            for (Account account : registeredAccounts) {
                if (account.id.equals(id)) {
                    return account;
                }
            }
            return null;
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addCustomer(Customer customer) {
            if (registeredAccounts == null)
                registeredAccounts = new ArrayList<>();
            registeredAccounts.add(customer);

            if (registeredCustomers == null)
                registeredCustomers = new ArrayList<>();
            registeredCustomers.add(customer);
        }

        public Customer getCustomerById(Long id) {
            for (Customer customer : registeredCustomers) {
                if (customer.id.equals(id)) {
                    return customer;
                }
            }
            return null;
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addRestaurant(Restaurant restaurant) {
            if (registeredAccounts == null)
                registeredAccounts = new ArrayList<>();
            registeredAccounts.add(restaurant);

            if (registeredRestaurants == null)
                registeredRestaurants = new ArrayList<>();
            registeredRestaurants.add(restaurant);
        }

        public Restaurant getRestaurantById(Long id) {
            for (Restaurant restaurant : registeredRestaurants) {
                if (restaurant.id.equals(id)) {
                    return restaurant;
                }
            }
            return null;
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addRider(Rider rider) {
            if (registeredAccounts == null)
                registeredAccounts = new ArrayList<>();
            registeredAccounts.add(rider);

            if (registeredRiders == null)
                registeredRiders = new ArrayList<>();
            registeredRiders.add(rider);
        }

        public Rider getRiderById(Long id) {
            for (Rider rider : registeredRiders) {
                if (rider.id.equals(id)) {
                    return rider;
                }
            }
            return null;
        }

    }

    protected static AccountManager accountManager = new AccountManager();

    /// Task 2: Implement the register method.
    public abstract void register();

    public static Account getAccountById(Long id) {
        return accountManager.getAccountById(id);
    }

    /// Do not modify this method.
    public static AccountManager getAccountManager() {
        return accountManager;
    }

}
