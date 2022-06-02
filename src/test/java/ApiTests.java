import configuration.ApiConfig;
import io.restassured.response.ValidatableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ApiTests {

    @Test
    public void apiAuthPropertiesTest() throws IOException {



        ApiConfig config = ConfigFactory.create(ApiConfig.class);

        String token = config.token();
        String baseUrl = config.baseUrl();

        int status = given()
                .auth().oauth2(token)
                .when()
                .get(baseUrl)
                .then()
                .extract().statusCode();
        assertThat(status).isEqualTo(200);
    }

    @Test
    public void apiAuthFileTest() throws IOException{
        String tokenProp = "token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Mjk2MTgyYzA0NmM5ZDAwM2QxNmYzNzQiLCJpYXQiOjE2NTQwMDM3NTYsImV4cCI6MTY1NDYwODU1Nn0.QP9ruhcO63TnGsmhSu47j_3bDdkm3F3B3PmbA7pR4-I";

        Path propsPath = Paths.get("/tmp/api.properties");
        Files.write(propsPath, tokenProp.getBytes(StandardCharsets.UTF_8));

        ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());
        String token = config.token();
        String baseUrl = config.baseUrl();

        int status = given()
                .auth().oauth2(token)
                .when()
                .get(baseUrl)
                .then()
                .extract().statusCode();
        assertThat(status).isEqualTo(200);

        Files.delete(propsPath);
    }
}