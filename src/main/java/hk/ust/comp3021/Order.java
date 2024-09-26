package hk.ust.comp3021;

import java.util.List
public class Order {
    private Long id;
    private Integer status;
    private Customer customer;
    private Long CreateTime;
    private Boolean isPayed;
    private List<Dish> orderedDishes;
    private Rider rider;
    private Double estimatedTime;
    public Double calculateEstimatedTime(){
        //For Task9
    }
}
