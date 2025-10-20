package api.endpoints;

public class Endpoints {

    public static final String GET_ACCOUNT_DETAILS = "/accounts/{accountId}";
    public static final String BILL_PAY = "/billpay";
    public static final String DEPOSIT = "/deposit";
    public static final String GET_ACCOUNT_BY_ID = "/accounts/{accountId}";
    public static final String GET_ACCOUNT_TRANSACTIONS = "/accounts/{accountId}/transactions";
    public static final String GET_TRANSACTIONS_BY_AMOUNT = "/accounts/{accountId}/transactions/amount/{amount}";
    public static final String GET_TRANSACTIONS_BY_MONTH_TYPE = "/accounts/{accountId}/transactions/month/{month}/type/{type}";
    public static final String GET_TRANSACTIONS_BY_DATE_RANGE = "/accounts/{accountId}/transactions/fromDate/{fromDate}/toDate/{toDate}";
    public static final String TRANSFER = "/transfer";
    public static final String WITHDRAW = "/withdraw";
    public static final String CREATE_ACCOUNT = "/createAccount";
    public static final String GET_CUSTOMER_ACCOUNTS = "/customers/{customerId}/accounts";
    public static final String GET_CUSTOMER_DETAILS ="/customers/{customerId}";
    public static final String GET_CUSTOMER_POSITIONS = "/customers/{customerId}/positions";
    public static final String UPDATE_CUSTOMER = "/customers/update/{customerId}";
    public static final String GET_TRANSACTION_BY_ID = "/transactions/{transactionId}";
    public static final String GET_TRANSACTIONS_BY_DATE = "/accounts/{accountId}/transactions/onDate/{onDate}";
}
