package steps.reqres;

import commondata.GlobalData;
import io.cucumber.java.en.Given;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import reader.PropertyReader;

//@Listeners(AutomationListener.class)
public class DeleteSteps {
    private RequestSpecification reqSpec;
    private Response response;
    private GlobalData globalData;

    public DeleteSteps(GlobalData globalData){
        this.globalData= globalData;
    }
    @Owner("Dinesh")
    @Given("Perform delete operation for {string}")
    public void perform_delete_operation_for(String endpoint) {

        try {
            reqSpec = RestAssured.given()
                    .baseUri(PropertyReader.getValue("URL1", "src/test/resources/config.properties"))
                    .log().all();
            response = reqSpec.when().delete(endpoint);
            globalData.setStatusCode(response.getStatusCode());
        } catch (Exception e) {
            System.err.println("Error occurred during DELETE operation: " + e.getMessage());
        }
    }
    
    
}
