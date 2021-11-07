package main;

public class Customers {
    private String firstName;
    private String lastName;
    private String address;
    private String panCardNumber;
    private long aadharCardNumber;
    private int balance;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public long getAadharCardNumber() {
        return aadharCardNumber;
    }

    public void setAadharCardNumber(long aadharCardNumber) {
        this.aadharCardNumber = aadharCardNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
