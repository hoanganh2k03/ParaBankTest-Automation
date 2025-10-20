package api.models;

public class TransactionResponse {
    private int id;
    private int accountId;
    private String description;
    private String type;
    private double amount;
    public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String date;

    public int getId() { return id; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
}
