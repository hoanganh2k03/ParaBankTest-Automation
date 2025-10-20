package api.models;

public class GetPositionsResponse {
    private int positionId;
    private int customerId;
    private String name;
    private String symbol;
    private int shares;
    private double purchasePrice;

    // Getters và setters
    public int getPositionId() { return positionId; }
    public void setPositionId(int positionId) { this.positionId = positionId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public int getShares() { return shares; }
    public void setShares(int shares) { this.shares = shares; }

    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }
}
