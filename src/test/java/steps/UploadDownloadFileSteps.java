package steps;

import constants.GlobalVars;
import servicehelpers.Get;
import io.cucumber.java.en.Given;
import settergetter.ThreadSafety;
import utilities.Common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class UploadDownloadFileSteps {

    @Given("^User performs file upload to '(.*)'$")
    public void fileUpload(String url) {
        
    }

    @Given("^User save response to file for '(.*)'$")
    public void fileDownload(String endpoint) throws IOException {
     
    }
}
