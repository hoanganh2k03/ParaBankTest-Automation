package api.models;

public class BillPayRequest {
    private String name;
    private Address address;
    private String phoneNumber;
    private int accountNumber;

    // Constructor
    public BillPayRequest(String name, Address address, String phoneNumber, int accountNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
    }

    // Getters
    public String getName() { return name; }
    public Address getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getAccountNumber() { return accountNumber; }
}
