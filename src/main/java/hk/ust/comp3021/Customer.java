package hk.ust.comp3021;


import java.util.List;

public class Customer extends Account{

    private Integer customerType;
    private String gender;
    private String email;

    public Customer(Long id, String accountType, String name, String contactNumber, List<Double> location, Integer customerType, String gender, String email) {
        super(id, accountType, name, contactNumber, location);
        this.customerType = customerType;
        this.gender = gender;
        this.email = email;
    }
    public void register(){
        //The abstract method for Task 2
        accountManager.addCustomer(this);
    }
    public Customer getCustomerById(Long id){
        //Get account from manager
        return accountManager.getCustomerById(id);
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    /// Do not modify this method.
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", location=" + location +
                ", customerType=" + customerType +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
