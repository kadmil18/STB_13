package organization;

import base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrganizationTest extends BaseTest {

    @Test
    @Order(1)
    public void createNewOrganization() {

        Response response = given()
                .spec(reqSpec)
                .queryParam("displayName", "MVPType")
                .queryParam("desc", "All-Time")
                .queryParam("name", "stb_new_organization")
                .queryParam("website", "https://alltime.org")
                .when()
                .post(BASE_URL + ORGANIZATION)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertThat(json.getString("displayName")).isEqualTo("MVPType");
        assertThat(json.getString("desc")).isEqualTo("All-Time");
        assertThat(json.getString("name")).isEqualTo("stb_new_organization");
        assertThat(json.getString("website")).isEqualTo("https://alltime.org");

        organizationId = json.get("id");
        String orgName = json.getString("displayName");

        assertThat(orgName).containsAnyOf("MVP");
        assertThat(orgName).isEqualTo("MVPType");

        assertThat(json.getString("website")).startsWith("https");
        assertThat(json.getString("website")).doesNotContain(".com");

        String compName = json.get("name");
        assertThat(compName).hasSizeGreaterThanOrEqualTo(3);
        assertThat((compName)).containsAnyOf("stb", "_");
    }

    @Test
    @Order(2)
    public void updateOrganization() {

        Response response = given()
                .spec(reqSpec)
                .queryParam("displayName", "NEWMVPType")
                .queryParam("desc", "NEWAll-Time")
                .queryParam("name", "new_stb_new_organization")
                .queryParam("website", "https://newalltime.org")
                .when()
                .put(BASE_URL + ORGANIZATION + organizationId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertThat(json.getString("displayName")).isEqualTo("NEWMVPType");
        assertThat(json.getString("desc")).isEqualTo("NEWAll-Time");
        assertThat(json.getString("name")).isEqualTo("new_stb_new_organization");
        assertThat(json.getString("website")).isEqualTo("https://newalltime.org");


    }

    @Test
    @Order(3)
    public void deleteOrganization() {
        given()
                .spec(reqSpec)
                .when()
                .delete(BASE_URL + ORGANIZATION + organizationId)
                .then()
                .statusCode(200);
    }
}
