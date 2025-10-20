package tests.api;

import api.endpoints.Endpoints;
import api.models.Address;
import api.models.BillPayResponse;
import api.models.CreateAccountResponse;
import api.models.GetAccountResponse;
import api.models.TransactionResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import utils.ConfigReader;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;

import org.testng.annotations.Test;
import api.client.SpecBuilder;
import api.models.BillPayRequest;
public class AccountApiTest {

	
	


@Test
public void testBillPay() {
    Address address = new Address("123 Main St", "Hanoi", "HN", "10000");
    BillPayRequest request = new BillPayRequest("EVN", address, "0123456789", Integer.parseInt(ConfigReader.getProperty("accountId")));

    BillPayResponse response =
        RestAssured.given(SpecBuilder.getRequestSpec())
            .queryParam("accountId", Integer.parseInt(ConfigReader.getProperty("accountId")))
            .queryParam("amount", 200.0)
            .body(request)
        .when()
            .post(Endpoints.BILL_PAY)
        .then()
            .spec(SpecBuilder.getResponseSpec())
            .assertThat().statusCode(200)
            .extract()
            .as(BillPayResponse.class, ObjectMapperType.JACKSON_2);

    System.out.println("✅ Bill payment success!");
}
@Test
public void testDeposit() {
    int accountId = Integer.parseInt(ConfigReader.getProperty("accountId"));
    double amount = 500.00;

    // Gửi request POST /deposit?accountId=...&amount=...
    String response =
        RestAssured.given(SpecBuilder.getRequestSpec())
            .queryParam("accountId", accountId)
            .queryParam("amount", amount)
        .when()
            .post(Endpoints.DEPOSIT)
        .then()
            .spec(SpecBuilder.getResponseSpec())
            .assertThat().statusCode(200)
            .extract()
            .asString();

    // In response và kiểm tra kết quả
    System.out.println("✅ Deposit response: " + response);
    assertThat(response, not(emptyOrNullString()));
}
@Test
public void testGetAccountById() {
    int accountId = Integer.parseInt(ConfigReader.getProperty("accountId"));

    GetAccountResponse response =
        RestAssured.given(SpecBuilder.getRequestSpec())
            .pathParam("accountId", accountId)
        .when()
            .get(Endpoints.GET_ACCOUNT_BY_ID)
        .then()
            .spec(SpecBuilder.getResponseSpec())
            .assertThat().statusCode(200)
            .extract()
            .as(GetAccountResponse.class, ObjectMapperType.JACKSON_2);

    System.out.println("✅ Account Info: ID=" + response.getId() + ", Balance=" + response.getBalance());
    assertThat(response.getId(), equalTo(accountId));
    assertThat(response.getBalance(), greaterThanOrEqualTo(BigDecimal.ZERO));
}
@Test
public void testGetCustomerAccounts() {
    int customerId = Integer.parseInt(ConfigReader.getProperty("customerId"));

    GetAccountResponse[] accounts =
        RestAssured.given(SpecBuilder.getRequestSpec())
            .pathParam("customerId", customerId)
        .when()
            .get(Endpoints.GET_CUSTOMER_ACCOUNTS)
        .then()
            .spec(SpecBuilder.getResponseSpec())
            .assertThat().statusCode(200)
            .extract()
            .as(GetAccountResponse[].class, ObjectMapperType.JACKSON_2);

    System.out.println("✅ Total Accounts Found: " + accounts.length);
    assertThat(accounts.length, greaterThan(0));
}

@Test
public void testTransfer() {
    int fromAccountId = Integer.parseInt(ConfigReader.getProperty("accountId"));
    int toAccountId = 12345;
    double amount = 100.0;

    String response = RestAssured
            .given()
                .spec(SpecBuilder.getRequestSpec())
                .queryParam("fromAccountId", fromAccountId)
                .queryParam("toAccountId", toAccountId)
                .queryParam("amount", amount)
            .when()
                .post("/transfer")
            .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .asString();

    System.out.println("Response: " + response);
    assertThat(response.toLowerCase(), containsString("success"));
}
@Test
public void testWithdraw() {
    int accountId = Integer.parseInt(ConfigReader.getProperty("accountId"));
    double amount = 50.0;

    String response = RestAssured
            .given()
                .spec(SpecBuilder.getRequestSpec())
                .queryParam("accountId", accountId)
                .queryParam("amount", amount)
            .when()
                .post("/withdraw")
            .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .asString();

    System.out.println("Response: " + response);
    assertThat(response.toLowerCase(), containsString("success"));
}
}