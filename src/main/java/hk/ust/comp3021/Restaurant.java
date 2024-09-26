package hk.ust.comp3021;

import java.util.List;

public class Restaurant {

    private String district;
    private String street;
    private List<Dish> dishes;
    public void register(){
        //The abstract method for Task 2
    }
    public void addDish(Dish dish){
        //Add dish to list
        this.dishes.add(dish);
    }
    public void getRestaurantById(Long id){
        //Get account from manager
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
