package tests.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import api.client.SpecBuilder;
import api.endpoints.Endpoints;
import api.models.TransactionResponse;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import utils.ConfigReader;

public class TransactionApiTest {
	@Test
	public void testGetTransactionsByAccountId() {
	    int accountId = Integer.parseInt(ConfigReader.getProperty("accountId"));

	    TransactionResponse[] transactions =
	        RestAssured.given(SpecBuilder.getRequestSpec())
	            .pathParam("accountId", accountId)
	        .when()
	            .get(Endpoints.GET_ACCOUNT_TRANSACTIONS)
	        .then()
	            .spec(SpecBuilder.getResponseSpec())
	            .assertThat().statusCode(200)
	            .extract()
	            .as(TransactionResponse[].class, ObjectMapperType.JACKSON_2);

	    System.out.println("✅ Transactions count: " + transactions.length);
	    if (transactions.length > 0) {
	        System.out.println("➡ First transaction amount: " + transactions[0].getAmount());
	    }
	    assertThat(transactions, notNullValue());
	}
	@Test
	public void testGetTransactionsByAmount() {
	    int accountId = Integer.parseInt(ConfigReader.getProperty("accountId"));
	    double amount = 1.0;

	    TransactionResponse[] response =
	        RestAssured.given(SpecBuilder.getRequestSpec())
	            .pathParam("accountId", accountId)
	            .pathParam("amount", amount)
	        .when()
	            .get(Endpoints.GET_TRANSACTIONS_BY_AMOUNT)
	        .then()
	            .spec(SpecBuilder.getResponseSpec())
	            .statusCode(200)
	            .extract()
	            .as(TransactionResponse[].class);

	    assertThat(response.length, greaterThan(0));
	    assertThat(response[0].getAmount(), greaterThan(0.0));
	}
	@Test
	public void testGetTransactionsByMonthAndType() {
	    int accountId = Integer.parseInt(ConfigReader.getProperty("accountId"));
	    int month = 10;
	    String type = "1";

	    TransactionResponse[] response =
	        RestAssured.given(SpecBuilder.getRequestSpec())
	            .pathParam("accountId", accountId)
	            .pathParam("month", month)
	            .pathParam("type", type)
	        .when()
	            .get(Endpoints.GET_TRANSACTIONS_BY_MONTH_TYPE)
	        .then()
	            .spec(SpecBuilder.getResponseSpec())
	            .statusCode(200)
	            .extract()
	            .as(TransactionResponse[].class);

	    assertThat(response.length, greaterThanOrEqualTo(0));
	}
	@Test
	public void testGetTransactionsByDateRange() {
	    int accountId = Integer.parseInt(ConfigReader.getProperty("accountId"));
	    String fromDate = "2025-01-01";
	    String toDate = "2025-12-31";

	    TransactionResponse[] response =
	        RestAssured.given(SpecBuilder.getRequestSpec())
	            .pathParam("accountId", accountId)
	            .pathParam("fromDate", fromDate)
	            .pathParam("toDate", toDate)
	        .when()
	            .get(Endpoints.GET_TRANSACTIONS_BY_DATE_RANGE)
	        .then()
	            .spec(SpecBuilder.getResponseSpec())
	            .statusCode(200)
	            .extract()
	            .as(TransactionResponse[].class);

	    assertThat(response.length, greaterThanOrEqualTo(0));
	}
	@Test
	public void testGetTransactionsByDate() {
	    int accountId = Integer.parseInt(ConfigReader.getProperty("accountId"));
	    String onDate = "2025-10-18";

	    TransactionResponse[] response =
	        RestAssured.given(SpecBuilder.getRequestSpec())
	            .pathParam("accountId", accountId)
	            .pathParam("onDate", onDate)
	        .when()
	            .get(Endpoints.GET_TRANSACTIONS_BY_DATE)
	        .then()
	            .spec(SpecBuilder.getResponseSpec())
	            .statusCode(200)
	            .extract()
	            .as(TransactionResponse[].class);

	    assertThat(response.length, greaterThanOrEqualTo(0));
	}
	 @Test
	    public void testGetTransactionById() {
	        int transactionId = Integer.parseInt(ConfigReader.getProperty("transactionId"));
	        System.out.println("Running test: testGetTransactionById with ID = " + transactionId);
	        TransactionResponse response =
	                RestAssured.given(SpecBuilder.getRequestSpec())
	                        .pathParam("transactionId", transactionId)
	                .when()
	                        .get(Endpoints.GET_TRANSACTION_BY_ID)
	                .then()
	                        .spec(SpecBuilder.getResponseSpec())
	                        .assertThat().statusCode(200)
	                        .extract().as(TransactionResponse.class, ObjectMapperType.JACKSON_2);

	        System.out.println("✅ Transaction Details:");
	        System.out.println("ID: " + response.getId());
	        System.out.println("Type: " + response.getType());
	        System.out.println("Amount: " + response.getAmount());
	        assertThat(response.getId(), equalTo(transactionId));
	        assertThat(response.getAmount(), greaterThan(0.0));
	        assertThat(response.getType(), notNullValue());
	        System.out.println("HETTTT");
	    }
	
}