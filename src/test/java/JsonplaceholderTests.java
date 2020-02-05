import com.typicode.jsonplaceholder.environment.LoaderProperties;
import com.typicode.jsonplaceholder.objects.BodyPostDTO;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class JsonplaceholderTests {

    private BodyPostDTO requestPost;
    private StringBuilder comentario = new StringBuilder();

    @BeforeClass
    public void setUpTests() throws IOException {
        requestPost = BodyPostDTO.builder()
                        .userId("5")
                        .title("Teste")
                        .body("Teste de comentário")
                        .build();

        comentario.append("repudiandae veniam quaerat sunt sed ");
        comentario.append("alias aut fugiat sit autem sed est ");
        comentario.append("voluptatem omnis possimus esse voluptatibus quis ");
        comentario.append("est aut tenetur dolor neque");
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
                .basePath(LoaderProperties.getBasePathPosts())
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

    @Test
    public void ConsultarPost(){

        JsonPath response = given()
                .baseUri(LoaderProperties.getBaseUri())
                .basePath(LoaderProperties.getBasePathPosts())
            .when()
                .body(requestPost)
                .get("/5")
            .then()
                .statusCode(200)
                .extract().jsonPath();

        response.get("id").equals(5);
        response.get("userId").equals(1);
        response.get("title").equals("nesciunt quas odio");
        response.get("body").equals(comentario);
    }

    @Test
    public void DeletarPost(){

        given()
                .baseUri(LoaderProperties.getBaseUri())
                .basePath(LoaderProperties.getBasePathPosts())
            .when()
                .body(requestPost)
                .delete("/5")
            .then()
                .statusCode(200);
    }

    @Test
    public void DeletarPostNegativo(){

        given()
                .baseUri(LoaderProperties.getBaseUri())
                .basePath(LoaderProperties.getBasePathPosts())
                .when()
                .body(requestPost)
                .delete("/")
                .then()
                .statusCode(404);
    }

    @Test
    public void AlterarPostInexistente(){

        given()
                .baseUri(LoaderProperties.getBaseUri())
                .basePath(LoaderProperties.getBasePathPosts())
            .when()
                .body(requestPost)
                .put("/101")
            .then()
                .statusCode(500);
    }
}
