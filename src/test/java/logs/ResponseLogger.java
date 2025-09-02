package logs;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.testng.Reporter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class ResponseLogger implements Filter {

    public ResponseLogger() {

    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        ByteArrayOutputStream requestLog = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(requestLog);
        Set<String> hashset = new HashSet<>();
        hashset.add("token");
        Response response = ctx.next(requestSpec, responseSpec);
        String responseLog = ResponsePrinter.print(response, response, stream, LogDetail.ALL, true, hashset);
        Reporter.log(responseLog);
        return response;
    }
}