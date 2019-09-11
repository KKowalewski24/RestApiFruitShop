package pl.kkowalewski.springrestfruitshop.api.ver1.model.customer;

public class CustomerDto {

    /*------------------------ FIELDS REGION ------------------------*/
    private String firstName;
    private String lastName;
    private String customerUrl;

    /*------------------------ METHODS REGION ------------------------*/
    public CustomerDto() {
    }

    public CustomerDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerDto(String firstName, String lastName, String customerUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerUrl = customerUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerUrl() {
        return customerUrl;
    }

    public void setCustomerUrl(String customerUrl) {
        this.customerUrl = customerUrl;
    }
}
