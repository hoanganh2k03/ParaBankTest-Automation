package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // Bỏ qua các field không có trong class
public class CreateAccountResponse {

    @JsonProperty("id")
    private int accountId;   // biến class khác với JSON field "id"

    @JsonProperty("customerId")
    private String customer; // biến class khác với JSON field "customerId"

    // Getters
    public int getAccountId() {
        return accountId;
    }

    public String getCustomer() {
        return customer;
    }

    // Setters nếu cần
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
