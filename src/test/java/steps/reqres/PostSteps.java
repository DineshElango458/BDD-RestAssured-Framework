package steps.reqres;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import commondata.GlobalData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import logs.AlureLogger;
import logs.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import reader.PropertyReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Listeners(TestListener.class)
public class PostSteps {
    private RequestSpecification reqSpec;
    private Response response;
    private GlobalData globalData;
    private static final Logger logger = LogManager.getLogger(PostSteps.class);

    static {
        RestAssured.filters(new AlureLogger());
    }

    public PostSteps(GlobalData globalData) {
        this.globalData = globalData;
    }

    @When("Add user with {string} and {string} for {string} using jsonString")
    public void add_user_with_and_for_using_json_string(String name, String job, String bodytype) {
        reqSpec = RestAssured.given().baseUri(PropertyReader.getValue("URL1", "src/test/resources/config.properties"));
        String payload = "{\n" +
                "    \"name\": \"value1\",\n" +
                "    \"job\": \"value2\"\n" +
                "}";
        // Replace placeholders with actual values
        payload = payload.replace("value1", name)
                .replace("value2", job);
        logger.info("Payload Logging" + payload);
        response = reqSpec.when().body(payload).log().all().post(PropertyReader.getValue("post.endpoint", "src/test/resources/config.properties"));
        globalData.setStatusCode(response.getStatusCode());
        logger.info("Response Body ::" + response.getBody().asString());


    }

    @Then("Print createdAt")
    public void print_created_at() {
        String createdAt = response.jsonPath().get("createdAt");
        System.out.println("createdAt value is " + createdAt);
        logger.info(createdAt);
    }

    @When("Add user with {string} and {string} for {string} using hashmap")
    public void add_user_with_and_for_using_hashmap(String name, String job, String string3) {
        Map<String, String> mp = new LinkedHashMap<>();
        mp.put("name", name);
        mp.put("job", job);
        reqSpec = RestAssured.given().baseUri(PropertyReader.getValue("URL1", "src/test/resources/config.properties"));
        response = reqSpec.when().body(mp).log().all().post(PropertyReader.getValue("post.endpoint", "src/test/resources/config.properties"));
        String prettyString = response.getBody().asPrettyString();
        System.out.println(prettyString);
        globalData.setStatusCode(response.getStatusCode());
    }

    @When("Add user from {string} for {string}")
    public void add_user_from_for(String filePath, String endpoint) throws IOException {
        reqSpec = RestAssured.given().baseUri(PropertyReader.getValue("URL1", "src/test/resources/config.properties"));
        File jsonFile = new File(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(objectMapper.readTree(jsonFile));
        response = reqSpec.when().body(jsonBody).post(endpoint);
        System.out.println(response.prettyPrint());
        globalData.setStatusCode(response.getStatusCode());
    }

//Register steps

    @When("Add user with {string} and {string} for {string}")
    public void add_user_with_and_for(String email, String password, String endpoint) {
        reqSpec = RestAssured.given().baseUri(PropertyReader.getValue("URL1", "src/test/resources/config.properties")).contentType("application/json");
        Map<String, String> mp = new LinkedHashMap<>();
        mp.put("email", email);
        mp.put("password", password);
        response = reqSpec.when().body(mp).post(endpoint);
        globalData.setStatusCode(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Then("Print token")
    public void print_token() {
        String token = response.jsonPath().get("token").toString();
        System.out.println("token" + token);
        Assert.assertFalse(token.isEmpty(), "Token is empty");
        Assert.assertNotNull(response.getBody().asString(), "no response body");
    }

    @Then("error message {string} is displayed")
    public void error_message_is_displayed(String errorMessage) {
        String error = response.jsonPath().get("error").toString();
        Assert.assertEquals(error, errorMessage);
        System.out.println("error message is :: " + error + "::" + errorMessage);
    }

    @When("Add user for {string} using data table")
    public void add_user_for_using_data_table(String endpoint, DataTable dataTable) throws JsonProcessingException {
        List<List<String>> lists = dataTable.asLists();
        for (List<String> data : lists) {
            String name = data.get(0);
            String job = data.get(1);

            Map<String, String> user = new HashMap<>();
            user.put("name", name);
            user.put("job", job);

// Serialize the Map to JSON
            String requestBody = new ObjectMapper().writeValueAsString(user);
            reqSpec = RestAssured.given().baseUri(PropertyReader.getValue("URL1", "src/test/resources/config.properties"));
            response = reqSpec.when().body(requestBody).log().all().post(endpoint);
            globalData.setStatusCode(response.getStatusCode());
            System.out.println("Status Code ::" + response.getStatusCode());
            globalData.setId(Integer.parseInt(response.jsonPath().get("id")));
        }
    }
}