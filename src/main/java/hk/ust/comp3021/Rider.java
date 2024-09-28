package hk.ust.comp3021;

import java.util.List;

public class Rider extends Account{

    private String gender;
    private Integer status;
    private Double userRating;
    private Integer monthTaskCount;

    public Rider(Long id, String accountType, String name, String contactNumber, List<Double> location, String gender, Integer status, Double userRating, Integer monthTaskCount) {
        super(id, accountType, name, contactNumber, location);
        this.gender = gender;
        this.status = status;
        this.userRating = userRating;
        this.monthTaskCount = monthTaskCount;
    }
    public void register(){
        //The abstract method for Task 2
        accountManager.addRider(this);
    }
    public Rider getRiderById(Long id){
        //Get account from manager
        return accountManager.getRiderById(id);
    }
    /// Do not modify this method.
    @Override
    public String toString() {
        return "Rider{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", location=" + location +
                ", gender='" + gender + '\'' +
                ", status=" + status +
                ", userRating=" + userRating +
                ", monthTaskCount=" + monthTaskCount +
                '}';
    }
}
