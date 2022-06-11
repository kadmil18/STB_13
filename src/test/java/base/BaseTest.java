package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected static final String BASE_URL = "https://api.trello.com/1/";
    protected static final String BOARDS = "boards/";
    protected static final String LISTS = "lists/";
    protected static final String CARDS = "cards/";
    protected static final String ORGANIZATION = "organizations/";

//    próbowałem wykorzystać sposób jaki opisałeś tutaj, ale niestety coś mi nie bangla :< https://www.facebook.com/photo.php?fbid=266451944507931&set=p.266451944507931&type=3
//    protected static String key;
//    protected static String token;

    protected static final String KEY = "";
    protected static final String TOKEN = "";

    protected static String boardId;
    protected static String firstListId;
    protected static String secondListId;
    protected static String cardId;
    protected static String organizationId;

    protected static RequestSpecBuilder reqBuilder;
    protected static RequestSpecification reqSpec;

    @BeforeAll
    public static void beforeAll() {

//        key = System.getProperty("key");
//        token = System.getProperty("token");

        reqBuilder = new RequestSpecBuilder();
        reqBuilder.addQueryParam("key", KEY);
        reqBuilder.addQueryParam("token", TOKEN);
        reqBuilder.setContentType(ContentType.JSON);

        reqSpec = reqBuilder.build();
    }

}
