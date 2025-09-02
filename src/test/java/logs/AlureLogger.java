package logs;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;



public class AlureLogger implements Filter {

    //    @Override
//    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
//        StringBuilder logBuilder = new StringBuilder();
//
//        // Logging Request
//        logBuilder.append("=========== API REQUEST ===========\n\n")
//                .append("➡️ Method: ").append(requestSpec.getMethod()).append("\n")
//                .append("➡️ URI: ").append(requestSpec.getURI()).append("\n")
//                .append("➡️ Headers: ").append(requestSpec.getHeaders().toString()).append("\n\n");
//
//        if (requestSpec.getBody() != null) {
//            logBuilder.append("➡️ Body: \n").append(requestSpec.getBody().toString()).append("\n\n");
//        }
//
//        // Spacing for readability
//        logBuilder.append("\n===================================\n\n");
//
//        Response response = ctx.next(requestSpec, responseSpec);
//
//        // Logging Response
//        logBuilder.append("=========== API RESPONSE ===========")
//                .append("✅ Status: ").append(response.getStatusCode()).append("\n")
//                .append("✅ Headers: ").append(response.getHeaders().toString()).append("\n\n")
//                .append("✅ Body: \n").append(response.getBody().asPrettyString()).append("\n\n");
//
//        // Spacing for clarity
//        logBuilder.append("===================================");
//
//        // Attach the formatted log to Allure report
//        Allure.addAttachment("API Request & Response Log", "text/plain", logBuilder.toString());
//
//        return response;
//    }
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, io.restassured.specification.FilterableResponseSpecification responseSpec, FilterContext ctx) {
        // Logging Request
        Allure.step("API Request", () -> {
            Allure.addAttachment("Request Method", requestSpec.getMethod());
            Allure.addAttachment("Request URI", requestSpec.getURI());
            Allure.addAttachment("Request Headers", requestSpec.getHeaders().toString());
            if (requestSpec.getBody() != null) {
                Allure.addAttachment("Request Body", "application/json", requestSpec.getBody().toString(), "json");
            }
        });

        Response response = ctx.next(requestSpec, responseSpec);

        // Logging Response
        Allure.step("API Response", () -> {
            Allure.addAttachment("Response Status", String.valueOf(response.getStatusCode()));
            Allure.addAttachment("Response Headers", response.getHeaders().toString());
            Allure.addAttachment("Response Body", "application/json", response.getBody().asPrettyString(), "json");
        });

        return response;
    }
}
