package hk.ust.comp3021;

import java.util.List;
public class Order {
    private Long id;
    private Integer status;
    private Restaurant restaurant;
    private Customer customer;
    private Long CreateTime;
    private Boolean isPayed;
    private List<Dish> orderedDishes;
    private Rider rider;
    private Double estimatedTime;
    public Order(Long id, Integer status, Restaurant restaurant, Customer customer, Long CreateTime,
                 Boolean isPayed, List<Dish> orderedDishes, Rider rider) {
        this.id = id;
        this.status = status;
        this.restaurant = restaurant;
        this.customer = customer;
        this.CreateTime = CreateTime;
        this.isPayed = isPayed;
        this.orderedDishes = orderedDishes;
        this.rider = rider;
    }
    public Double calculateEstimatedTime(){
        //For Task9
        return 0.0;
    }
}
