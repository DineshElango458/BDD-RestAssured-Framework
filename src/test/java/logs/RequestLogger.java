package logs;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.testng.Reporter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class RequestLogger implements Filter {

    public RequestLogger() {

    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        String uri = requestSpec.getURI();
        uri = UrlDecoder.urlDecode(uri, Charset.forName(requestSpec.getConfig().getEncoderConfig().defaultQueryParameterCharset()), true);
        ByteArrayOutputStream requestLog = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(requestLog);
        Set<String> hashset = new HashSet<>();
        hashset.add("token");
        String request = RequestPrinter.print(requestSpec, requestSpec.getMethod(), uri, LogDetail.ALL, hashset, stream, true);
        Reporter.log(request);
        Response response = ctx.next(requestSpec, responseSpec);
        return response;
    }
}
