package steps.reqres;

import commondata.GlobalData;
import io.cucumber.java.en.Then;
import lombok.Data;
import org.testng.Assert;

@Data
public class CommonSteps {
private GlobalData globalData;

public CommonSteps(GlobalData globalData) {
    this.globalData=globalData;
}

    @Then("Status code is {int}")
    public void status_code_is(int statuscode) {
        Assert.assertEquals(globalData.getStatusCode(), statuscode);
    }

    @Then("Print id")
    public void print_id() {
    Assert.assertNotNull(globalData.getId(), "Id is missing in the respnse");
        System.out.println("Id is not null");

    }
}
