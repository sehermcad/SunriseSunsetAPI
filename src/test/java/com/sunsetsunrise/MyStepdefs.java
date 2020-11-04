package com.sunsetsunrise;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
	RequestSpecification response;

	@Given("I set GET request the Latitude as {string} and Longtitude as {string}")
	public void iEnterTheLatitudeAsAndLongtitudeAs(String lat, String longt) {
		System.out.println(":::STEP NUMBER 1");
		String baseURI = "https://api.sunrise-sunset.org";
		response = given()//.log().all()
				.baseUri(baseURI + "/json")
				.queryParam("lat", lat)
				.queryParam("lng", longt);
		;
		//System.out.println(response.get().prettyPeek());


	}


	@When("Verify valid HTTP response code {int}")
	public void verifyValidHTTPResponseCode(int statusCode) {
		System.out.println(":::STEP NUMBER 2");
		assertEquals(statusCode, response.get().getStatusCode());
		System.out.println("Assertion successful and we recevied " + response.get().getStatusCode());

	}

	@Then("Verify that we received the {string} and {string} times without date.")
	public void verifyThatWeReceivedTheAndTimesWithoutDate(String sunriseTime, String sunsetTime) {
		System.out.println(":::STEP NUMBER 3");

		JsonPath jsonPath= response.get().jsonPath();
		String sunset = jsonPath.getString("results.sunset");
		String sunrise = jsonPath.getString("results.sunrise");
		assertEquals(sunriseTime, sunrise);
		System.out.println("Assertion of sunrise successful : " + sunrise);
		assertEquals(sunsetTime, sunset);
		System.out.println("Assertion of sunset successful : " + sunset);

	}

	@And("Verify that we received the {string} and {string} times with given {string}")
	public void verifyThatWeReceivedTheAndWithGiven(String sunriseTime, String sunsetTime,String date) {
		System.out.println(":::STEP NUMBER 4");

		response
				.queryParam("date",date);

		JsonPath jsonPath= response.get().jsonPath();
		String sunset = jsonPath.getString("results.sunset");
		String sunrise = jsonPath.getString("results.sunrise");
		System.out.println("Date is : "+date);
		//System.out.println(response.get().prettyPeek());

		assertEquals(sunriseTime, sunrise);
		System.out.println("Assertion of sunrise successful : " + sunrise);
		assertEquals(sunsetTime, sunset);
		System.out.println("Assertion of sunset successful : " + sunset);

	}

	@And("Request an {string} response returns unformatted {string} when {string}")
	public void requestAnResponseReturnsUnformattedWhen(String formatted, String sunriseTime, String sunsetTime) {
		System.out.println(":::STEP NUMBER 5");
		response.queryParam("formatted", formatted);

		JsonPath jsonPath= response.get().jsonPath();
		String sunset = jsonPath.getString("results.sunset");
		String sunrise = jsonPath.getString("results.sunrise");

		assertEquals(sunriseTime, sunrise);
		System.out.println("Assertion of sunrise successful : " + sunrise);
		assertEquals(sunsetTime, sunset);
		System.out.println("Assertion of sunset successful : " + sunset);
	}


	@And("Verify passing an invalid {string} returns with a status of {string}")
	public void verifyPassingAnReturnsWithAStatusOf(String date, String status) {
		System.out.println(":::STEP NUMBER 6");
		response.queryParam("date", date);

		JsonPath jsonPath= response.get().jsonPath();
		String checkStatus = jsonPath.getString("status");

		assertEquals(status, checkStatus);
		System.out.println("Status : " + status);
	}

	@And("Verify the {string} between Sunrise and Sunset of {string}")
	public void verifyTheBetweenAndOf(String length, String date) {
		System.out.println(":::STEP NUMBER 7");

		response.queryParam("date",date);

		JsonPath jsonPath= response.get().jsonPath();
		String dayLength = jsonPath.getString("results.day_length");

		assertEquals(length, dayLength);
		System.out.println("Day Length : " + length);

	}
}
