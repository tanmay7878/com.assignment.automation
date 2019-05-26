package com.vend.testcases;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class TC_03_API_Manager_Permission_Change {

	private String response;
	private int statusCode;
	int mgrRole;

	/**
	 * ################################################################################################################################
	 * TC_001_GetManagerRole Description: A restAssured web service test to get the manager role whose permission 
	 * is going to be updated/or changed in terms of applying the discount on items and storing it's value into global variable i.e. 'mgrRole' 
	 * Precondition - N/A Test Case Type: Positive
	 * @param : N/A
	 * @throws N/A
	 * @Author: Hitesh Ghai
	 * @Expected Result: Manager role should be fetched successfully
	 * 
	 *           ################################################################################################################################
	 */

	@Test
	public void TC_001_GetManagerRole() {

		RestAssured.baseURI = "https://secure.vendhq.com/";

		Response res = given().auth().oauth2("5T7BNkaF1iywahWuhHQEO_eXfCncf5avfoO213mA")
				.param("client_id", "aj1qjnYLgmzpToeDpqGU40RqFAkjonVh")
				.param("redirect_uri", "https://bestquality.vendhq.com").param("state", "Auckland")
				.header("content-type ", "application/json").when().get("api/1.0/roles").then().assertThat()
				.statusCode(200).extract().response();
		/*
		 * response = get(
		 * "https://secure.vendhq.com/api/1.0/roles/connect?response_type=code" +
		 * "&client_id=aj1qjnYLgmzpToeDpqGU40RqFAkjonVh&redirect_uri=https://bestquality.vendhq.com"
		 * + "&state=Auckland&beststore=5T7BNkaF1iywahWuhHQEO_eXfCncf5avfoO213mA")
		 * .asString();
		 */
		statusCode = get("https://secure.vendhq.com/api/1.0/roles/connect?response_type=code"
				+ "&client_id=aj1qjnYLgmzpToeDpqGU40RqFAkjonVh&redirect_uri=https://bestquality.vendhq.com"
				+ "&state=Auckland&beststore=5T7BNkaF1iywahWuhHQEO_eXfCncf5avfoO213mA").statusCode();
		if (statusCode == 200) {
			JsonPath jp = new JsonPath(response);
			List<Integer> roles = from(response).getList("data.results.roles");
			System.out.println("Roles are    " + roles);
			for (Integer role : roles) {
				if (role == 1) {
					mgrRole = role;
				}
			}

		}
	}
	
	/** ################################################################################################################################
	 * TC_002_UpdateManagerPermission
	 * Description: A restAssured web service test to 'update' or 'disable' the permission for applying the discount (fetched from above test) 
	 * to the manager role fetched from the above test.
	 * Precondition - N/A
	 * Test Case Type: Positive
	 * @param : N/A
	 * @throws N/A
	 * @Author: Hitesh Ghai
	 * @Expected Result: Permission 'not' to apply discount on items for the manager should be applied successfully
	 * 
	 * ################################################################################################################################
	 */
	@Test
	public void TC_002_UpdateManagerPermission() {
		RestAssured.baseURI = "https://secure.vendhq.com/";

		/*
		 * Response resp =
		 * given().auth().oauth2("5T7BNkaF1iywahWuhHQEO_eXfCncf5avfoO213mA")
		 * //.header("Authorization", "Bearer"+"37cb9e58-99db-423c-9da5-42d5627614c5")
		 * .body("{}"). //.body("{}"). when(). post("api/1.0/roles/"+"mgrRole"); int
		 * responseCode = resp.getStatusCode(); System.out.println("Status code is -->"
		 * + responseCode);
		 */

		RestAssured.baseURI = "http://216.10.245.166";
		given().queryParam("beststore", "5T7BNkaF1iywahWuhHQEO_eXfCncf5avfoO213mA")
				.queryParam("client_id", "aj1qjnYLgmzpToeDpqGU40RqFAkjonVh")
				.queryParam("redirect_uri", "https://bestquality.vendhq.com").queryParam("state", "Auckland")
				.body("{\"name\":update} + \"value\":1").when().post("api/1.0/roles/" + "mgrRole").then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK")).log().all();

	}

}
