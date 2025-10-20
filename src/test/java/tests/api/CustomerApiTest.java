package tests.api;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.util.List;

import org.testng.annotations.Test;

import api.client.SpecBuilder;
import api.endpoints.Endpoints;
import api.models.CreateAccountResponse;
import api.models.GetAccountResponse;
import api.models.GetCustomerResponse;
import api.models.GetPositionsResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import utils.ConfigReader;
import static org.hamcrest.Matchers.*;
public class CustomerApiTest {
@Test
	public void testCreateAccount() {
	    int customerId = Integer.parseInt(ConfigReader.getProperty("customerId"));
	    int newAccountType = 1;
	    int fromAccountId = Integer.parseInt(ConfigReader.getProperty("accountId"));

	    CreateAccountResponse response =
	        RestAssured.given(SpecBuilder.getRequestSpec())
	            .queryParam("customerId", customerId)
	            .queryParam("newAccountType", newAccountType)
	            .queryParam("fromAccountId", fromAccountId)	      
	            .when()
	            .post(Endpoints.CREATE_ACCOUNT) // ví dụ: "/accounts/{customerId}/{newAccountType}/{fromAccountId}"
	        .then()
	            .spec(SpecBuilder.getResponseSpec())
	            .assertThat().statusCode(200)
	            .extract()
	            .as(CreateAccountResponse.class, ObjectMapperType.JACKSON_2);

	    assertThat(response.getAccountId(), greaterThan(0));
	    System.out.println("✅ Created Account ID: " + response.getAccountId());
	}
@Test
public void testGetAccountDetails() {
    int accountId = Integer.parseInt(ConfigReader.getProperty("accountId"));
    GetAccountResponse response =
            RestAssured.given(SpecBuilder.getRequestSpec())
                    .pathParam("accountId", accountId)
            .when()
                    .get(Endpoints.GET_ACCOUNT_DETAILS)
            .then()
                    .spec(SpecBuilder.getResponseSpec())
                    .assertThat().statusCode(200)
                    .extract().as(GetAccountResponse.class,ObjectMapperType.JACKSON_2);

    System.out.println("✅ Account Details:");
    System.out.println("ID: " + response.getId());
    System.out.println("Type: " + response.getType());
    System.out.println("Balance: " + response.getBalance());

    // Kiểm tra giá trị cơ bản
    assertThat(response.getId(), equalTo(accountId));
    assertThat(response.getBalance(), greaterThanOrEqualTo(java.math.BigDecimal.ZERO));
}
@Test
public void testGetCustomerDetails() {
    int customerId = Integer.parseInt(ConfigReader.getProperty("customerId"));

    GetCustomerResponse response =
            RestAssured.given(SpecBuilder.getRequestSpec())
                    .pathParam("customerId", customerId)
            .when()
                    .get("/customers/{customerId}")
            .then()
                    .spec(SpecBuilder.getResponseSpec())
                    .assertThat().statusCode(200)
                    .extract().as(GetCustomerResponse.class, ObjectMapperType.JACKSON_2);

    System.out.println("✅ Customer Details:");
    System.out.println("Name: " + response.getFirstName() + " " + response.getLastName());
    System.out.println("Phone: " + response.getPhoneNumber());
    System.out.println("Address: " + response.getAddress().getStreet());

    assertThat(response.getId(), equalTo(customerId));
    assertThat(response.getFirstName(), notNullValue());
}
@Test
public void testGetCustomerPositions() {
    int customerId = Integer.parseInt(ConfigReader.getProperty("customerId"));

    List<GetPositionsResponse> response =
            RestAssured.given(SpecBuilder.getRequestSpec())
                    .pathParam("customerId", customerId)
            .when()
                    .get("/customers/{customerId}/positions")
            .then()
                    .spec(SpecBuilder.getResponseSpec())
                    .assertThat().statusCode(200)
                    .extract().as(new TypeRef<List<GetPositionsResponse>>() {});

    System.out.println("✅ Positions for Customer:");
    for (GetPositionsResponse pos : response) {
        System.out.println(pos.getName() + " | " + pos.getSymbol() +
                           " | Shares: " + pos.getShares());
    }

    assertThat(response, not(empty()));
}
@Test
public void testUpdateCustomerInfo() {
    int customerId = Integer.parseInt(ConfigReader.getProperty("customerId"));

    String response =
            RestAssured.given(SpecBuilder.getRequestSpec())
                    .pathParam("customerId", customerId)
                    .queryParam("firstName", "John")
                    .queryParam("lastName", "Doe")
                    .queryParam("street", "123 Main St")
                    .queryParam("city", "New York")
                    .queryParam("state", "NY")
                    .queryParam("zipCode", "10001")
                    .queryParam("phoneNumber", "1234567890")
                    .queryParam("ssn", "111-22-3333")
                    .queryParam("username", "johndoe")
                    .queryParam("password", "password123")
            .when()
                    .post(Endpoints.UPDATE_CUSTOMER)
            .then()
                    .spec(SpecBuilder.getResponseSpec())
                    .assertThat().statusCode(200)
                    .extract().asString();

    System.out.println("✅ Update Customer Response: " + response);
    assertThat(response.toLowerCase(), containsString("success"));
}
}