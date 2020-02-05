import com.typicode.jsonplaceholder.environment.LoaderProperties;
import com.typicode.jsonplaceholder.objects.BodyPostDTO;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class JsonplaceholderTests {

    private BodyPostDTO requestPost;

    @BeforeClass
    public void setUpTests(){
        requestPost = BodyPostDTO.builder()
                        .userId("5")
                        .title("Teste")
                        .body("Teste de comentário")
                        .build();
    }

    @Test
    public void ValidarUsuario(){
        given()
                .baseUri(LoaderProperties.getBaseUri())
                .basePath(LoaderProperties.getBasePathUsers())
            .when()
                .get("/5")
            .then()
                .statusCode(200)
                .body("id", equalTo(5))
                .body("name", equalTo("Chelsey Dietrich"))
                .body("username", equalTo("Kamren"))
                .body("email", equalTo("Lucio_Hettinger@annie.ca"))
                .body("address.street", is("Skiles Walks"))
                .body("address.suite", is("Suite 351"))
                .body("address.city", is("Roscoeview"))
                .body("address.zipcode", is("33263"))
                .body("address.geo.lat", is("-31.8129"))
                .body("address.geo.lng", is("62.5342"))
        ;
    }

    @Test
    public void CriarComentario(){
        given()
                .baseUri(LoaderProperties.getBaseUri())
                .basePath(LoaderProperties.getBasePathUsers())
                .contentType("application/json; charset=UTF-8")
            .when()
                .body(requestPost)
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo(requestPost.getTitle()))
                .body("body", equalTo(requestPost.getBody()))
                .body("userId", equalTo(requestPost.getUserId()))
        ;
    }

    public void ConsultarComentarioCriado(){
        given()
                .baseUri(LoaderProperties.getBaseUri())
                .basePath(LoaderProperties.getBasePathUsers())
                .contentType("application/json; charset=UTF-8")
            .when()
                .body(requestPost)
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo(requestPost.getTitle()))
                .body("body", equalTo(requestPost.getBody()))
                .body("userId", equalTo(requestPost.getUserId()))
        ;
    }
}
