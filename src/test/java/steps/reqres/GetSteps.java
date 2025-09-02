package steps.reqres;

import commondata.GlobalData;
import constants.GlobalVars;
import servicehelpers.Get;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import settergetter.ThreadSafety;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetSteps {
	RequestSpecification reqSpec;
	public RequestSpecification req;
	Response response;
	private GlobalData globalData;

	public GetSteps (GlobalData globalData)  {
		this.globalData = globalData;
	}


	@Given("Perform get operation for {string}")
	public void perform_get_operation_for(String apiPath) throws IOException {

		reqSpec = given().spec(requestSpecification("get.baseUrl"));
		response = reqSpec.when().get(apiPath);   

		System.out.println("response" + response.getBody().prettyPrint());
globalData.setStatusCode(response.getStatusCode());

	}
//	@Then("Status code is {int}")
//	public void status_code_is(int statuscode) {
//		//Assert.assertEquals(response.getStatusCode(), statuscode);
//	}

	@Given("Perform get operation for {string} for JSON Schema Validation")
	public void perform_get_operation_for_for_json_schema_validation(String apiPath) throws IOException {
		reqSpec = given().spec(requestSpecification("get.baseUrl"));
		response = reqSpec.when().get(apiPath);  
		System.out.println("response" + response.getBody().prettyPrint());
	}

	@Then("Validate response using JSON file {string}")
	public void validate_response_using_json_file(String fileName) {
		InputStream JSONSchema = getClass().getClassLoader().getResourceAsStream(fileName);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JSONSchema));
	}

	@Then("{string} are {int} for {string}")
	public void are_for(String field, int count, String apiPath) {	
		JsonPath jsonPathEvaluator = response.jsonPath();
		int fields = jsonPathEvaluator.get(field);		
		Assert.assertEquals(fields, count);
	}

	@Then("IDs are displayed for {string}")
	public void i_ds_are_displayed_for(String apiPath) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		int size= jsonPathEvaluator.getList("data").size();
		System.out.println("Total IDs for this api path  " +size);
		String ID = null; 
		for (int i=0;i<size;i++) {
			ID = jsonPathEvaluator.getString("data["+i+"].id");
			System.out.println("ID"+i+" is "  +ID);
		}

	} 

	@Then("{string} is {string} for {string}")
	public void is_for(String fieldname, String actualname, String apipath) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		String fields = jsonPathEvaluator.getString("data."+fieldname);
				
		Assert.assertEquals(fields, actualname);

	}

	
	@Given("Perform query get operation for {string}")
	public void perform_query_get_operation_for(String string) {
	    // Write code here that turns the phrase above into concrete actions
	   
	}


	public RequestSpecification requestSpecification(String baseUrlProperty) throws IOException{
		if (req==null){
			//headersCRS=getHeadersCRS(requestType);
			req=new RequestSpecBuilder().setBaseUri(getProperty(baseUrlProperty))
					.setContentType(ContentType.JSON)
					//.addHeaders(headersCRS)
					.build();
		}
		return req;
	}
	public static String getProperty(String key) throws IOException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(Paths.get("").toAbsolutePath().toString()+"/src/test/resources/application.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

}
