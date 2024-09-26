package hk.ust.comp3021;

import java.util.List;

public class Rider {

    private String gender;
    private Integer status;
    private Double userRating;
    private Integer monthTaskCount;
    public void register(){
        //The abstract method for Task 2
    }
    public void getRiderById(Long id){
        //Get account from manager
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
