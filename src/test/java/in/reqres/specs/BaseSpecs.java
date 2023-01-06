package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class BaseSpecs {
    public static RequestSpecification baseRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification baseResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();
}
