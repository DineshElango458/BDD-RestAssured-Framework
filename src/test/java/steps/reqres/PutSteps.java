package steps.reqres;

import commondata.GlobalData;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.log4testng.Logger;
import reader.PropertyReader;
import io.cucumber.java.en.Given;

public class PutSteps {
private RequestSpecification reqSpec;
private Response response;
private GlobalData globalData;

public PutSteps(GlobalData globalData){
    this.globalData= globalData;
}
    @Given("Perform put operation for {string}")
    public void perform_put_operation_for(String endpoint) {
        try {
            String payload = "{\n" +
                    "    \"name\": \"morpheus\",\n" +
                    "    \"job\": \"zion resident\"\n" +
                    "}";
            reqSpec = RestAssured.given()
                    .baseUri(PropertyReader.getValue("URL1", "src/test/resources/config.properties"))
                    .log().all();

            response = reqSpec.body(payload).put(endpoint);
            Logger.getLogger(PutSteps.class).info(endpoint);
            globalData.setStatusCode(response.getStatusCode());
        } catch (Exception e) {
            System.err.println("Error occurred during PUT operation: " + e.getMessage());
        }
    }
    @Then("Print updatedAt")
    public void print_updated_at() {
        String updatedAt = response.jsonPath().get("updatedAt").toString();
        System.out.println(updatedAt);
    }


}

