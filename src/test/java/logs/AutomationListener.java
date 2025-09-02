package logs;

import io.qameta.allure.Allure;
import org.testng.*;

import java.io.File;
import java.util.List;

//public class AutomationListener implements ISuiteListener, ITestListener, IInvokedMethodListener {
//
//    public AutomationListener () {
//
//    }
//
//    @Override
//    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
//
//    }
//
//    @Override
//    public void onStart(ISuite suite) {
//    try {
//    File file = new File("./allure-results");
//    for (File f : file.listFiles()) {
//        if (f.getName().contains(".txt")) {
//            f.delete();
//        }
//        if (f.getName().contains("container.json")) {
//            f.delete();
//        }
//        if (f.getName().contains("result.json")) {
//            f.delete();
//        }
//    }
//} catch (Exception e){
//    e.printStackTrace();
//}
//    }
//
//    @Override
//    public void onFinish(ISuite suite) {
//
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        String name = result.getName();
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        String name = result.getName();
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        Reporter.log("Test Failed: " + result.getName());
//        Throwable throwable = result.getThrowable();
//        if (throwable != null) {
//            Reporter.log("Failure Reason: " + throwable.getMessage());
//        }
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        String name = result.getName();
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//
//    }
//
//    @Override
//    public void onStart(ITestContext context) {
//
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//
//    }
//
//    @Override
//    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
//        if (testResult.getStatus()==ITestResult.FAILURE || testResult.getStatus()==ITestResult.SKIP || testResult.getStatus()==ITestResult.SUCCESS) {
//            List<String> output = Reporter.getOutput(testResult);
//            attachLogsAllureReport(output);
//            Reporter.clear();
//        }
//    }
//
//    private void attachLogsAllureReport(List<String> output) {
//        for (String obj : output){
//            Allure.addAttachment("Logs", obj);
//        }
//    }
//
//}
