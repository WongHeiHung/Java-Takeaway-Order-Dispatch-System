package hk.ust.comp3021;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Restaurant extends Account{

    private String district;
    private String street;
    private List<Dish> dishes;

    public Restaurant(Long id, String accountType, String name, String contactNumber, List<Double> location, String district, String street) {
        super(id, accountType, name, contactNumber, location);
        this.district = district;
        this.street = street;
        this.dishes = new ArrayList<>();
    }
    public void register(){
        //The abstract method for Task 2
        accountManager.addRestaurant(this);
    }
    public void addDish(Dish dish){
        //Add dish to list
        this.dishes.add(dish);
    }
    public Restaurant getRestaurantById(Long id){
        //Get account from manager
        return accountManager.getRestaurantById(id);
    }
    public Long getId() {
        return this.id;
    }
    /// Do not modify this method.
    @Override
    public String toString() {
        List<Long> dishIds = new LinkedList<>(dishes.stream().map(Dish::getId).toList());
        dishIds.sort(Long::compareTo);
        return "Restaurant{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", location=" + location +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", dishIds='" + dishIds + '\'' +
                '}';
    }
}
