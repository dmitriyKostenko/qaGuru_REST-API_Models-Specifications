package in.reqres.tests;

import in.reqres.models.lombok.RegistrationBodyLombokModel;
import in.reqres.models.lombok.UserBodyLombokModel;
import in.reqres.models.pojo.RegistrationBodyPojoModel;
import in.reqres.models.pojo.UserBodyPojoModel;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.BaseSpecs.baseRequestSpec;
import static in.reqres.specs.BaseSpecs.baseResponseSpec;
import static in.reqres.specs.RegistrationSpecs.registrationRequestSpec;
import static in.reqres.specs.RegistrationSpecs.registrationResponseSpec;
import static in.reqres.specs.CreateUserSpecs.createUserRequestSpec;
import static in.reqres.specs.CreateUserSpecs.createUserResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {

    @Test
    void registrationSuccessfulLombokTest() {
        RegistrationBodyLombokModel registrationBody = new RegistrationBodyLombokModel();
        registrationBody.setEmail("eve.holt@reqres.in");
        registrationBody.setPassword("pistol");
        given()
                .spec(registrationRequestSpec)
                .body(registrationBody)
                .when()
                .post()
                .then()
                .spec(registrationResponseSpec)
                .body("id", is(4), "token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void registrationSuccessfulPojoTest() {
        RegistrationBodyPojoModel registrationBody = new RegistrationBodyPojoModel();
        registrationBody.setEmail("eve.holt@reqres.in");
        registrationBody.setPassword("pistol");
        given()
                .spec(registrationRequestSpec)
                .body(registrationBody)
                .when()
                .post()
                .then()
                .spec(registrationResponseSpec)
                .body("id", is(4), "token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void registrationUnsuccessfulLombokTest() {
        RegistrationBodyLombokModel registrationBody = new RegistrationBodyLombokModel();
        registrationBody.setEmail("eve.holt@reqres.in");
        given()
                .spec(registrationRequestSpec)
                .body(registrationBody)
                .when()
                .post()
                .then()
                .spec(baseResponseSpec)
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void registrationUnsuccessfulPojoTest() {
        RegistrationBodyPojoModel registrationBody = new RegistrationBodyPojoModel();
        registrationBody.setEmail("eve.holt@reqres.in");
        given()
                .spec(registrationRequestSpec)
                .body(registrationBody)
                .when()
                .post()
                .then()
                .spec(baseResponseSpec)
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void createUserLombokTest() {
        UserBodyLombokModel userBody = new UserBodyLombokModel();
        userBody.setName("morpheus");
        userBody.setJob("leader");
        given()
                .spec(createUserRequestSpec)
                .body(userBody)
                .when()
                .post()
                .then()
                .spec(createUserResponseSpec)
                .body("name", is("morpheus"), "job", is("leader"));
    }

    @Test
    void createUserPojoTest() {
        UserBodyPojoModel userBody = new UserBodyPojoModel();
        userBody.setName("morpheus");
        userBody.setJob("leader");
        given()
                .spec(createUserRequestSpec)
                .body(userBody)
                .when()
                .post()
                .then()
                .spec(createUserResponseSpec)
                .body("name", is("morpheus"), "job", is("leader"));
    }

    @Test
    void getListUsersTest() {
        given()
                .spec(baseRequestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(baseResponseSpec)
                .statusCode(200)
                .body("data.id", hasItems(7, 8, 9, 10, 11, 12),
                        "support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    void deleteUserTest() {
        given()
                .spec(baseRequestSpec)
                .when()
                .delete("/api/users/2")
                .then()
                .spec(baseResponseSpec)
                .statusCode(204);
    }
}
