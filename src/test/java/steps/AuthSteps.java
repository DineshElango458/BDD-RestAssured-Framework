package steps;

import utilities.Authentication;
import io.cucumber.java.en.Given;
import settergetter.ThreadSafety;

public class AuthSteps {

    @Given("^Using performs basic auth to '(.*)' with '(.*)' and '(.*)'$")
    public void basicAuth(String url, String username, String pass) {
        ThreadSafety.setResponse(Authentication.getResponseUsingBasicAuth(url, username, pass));
        ThreadSafety.setStatusCode(ThreadSafety.getResponse().getStatusCode());
    }

    
}
