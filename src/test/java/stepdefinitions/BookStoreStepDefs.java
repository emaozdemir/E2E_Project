package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import pojos.BookStorePojos.BookPojo;

import pojos.BookStorePojos.UsernamePassword;
import utilities.ObjectMapperUtils;

import static base_urls.BookStoreBaseUrl.spec;//DİKKAT!!!
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class BookStoreStepDefs {

    Response response;
    public static String username;
    public static String userID;
    UsernamePassword expectedData;
    BookPojo expectedDataBook;

    @Given("set the url for all books")
    public void set_the_url_for_all_books() {

        spec.pathParams("first", "BookStore", "second", "v1", "third", "Books");

    }

    @When("send the get request for all books")
    public void send_the_get_request_for_all_books() {

        response = given(spec).get("{first}/{second}/{third}");
        response.prettyPrint();

    }

    @Then("do assertion for all books")
    public void do_assertion_for_all_books() {

        assertEquals(200, response.statusCode());


    }

    @And("there must be {int} books")
    public void thereMustBeBooks(int number) {
        assertEquals(number, response.jsonPath().getList("books").size());
    }

    @And("first book must be {string}")
    public void firstBookMustBe(String bookTitle) {
        String firstBook = response.jsonPath().getString("books[0].title");
        assertEquals(bookTitle, firstBook);
    }


    @Given("set the url for account creation")
    public void setTheUrlForAccountCreation() {

        spec.pathParams("first", "Account", "second", "v1", "third", "User");

    }

    @And("set the expected data for account creation")
    public void setTheExpectedDataForAccountCreation() {

        String strPayload = """
                {
                    "userName": "username",
                    "password": "Clarusway@2024"
                }""";

        expectedData = ObjectMapperUtils.convertJsonStrToJava(strPayload, UsernamePassword.class);
        username = Faker.instance().name().username();
        expectedData.setUserName(username);

    }

    @When("send the post request for account creation")
    public void sendThePostRequestForAccountCreation() {
        response = given(spec).contentType(ContentType.JSON).body(expectedData).post("{first}/{second}/{third}");
        response.prettyPrint();
        userID = response.jsonPath().getString("userID");
    }

    @Then("do assertion for account creation")
    public void doAssertionForAccountCreation() {
        response.then().statusCode(201);
    }


    @Given("set the url for book assign")
    public void setTheUrlForBookAssign() {

        spec.pathParams("first", "BookStore", "second", "v1", "third", "Books");

    }

    @And("set the expected data for book assign")
    public void setTheExpectedDataForBookAssign() {

        String strPayload = """
                {
                  "userId": "{{userId}}",
                  "collectionOfIsbns": [
                    {
                      "isbn": "{{isbn}}"
                    }
                  ]
                }""";

        expectedDataBook = ObjectMapperUtils.convertJsonStrToJava(strPayload, BookPojo.class);
        expectedDataBook.setUserId(userID);
        expectedDataBook.getCollectionOfIsbns().get(0).setIsbn("9781449325862");


    }

    @When("send the post request for book assign")
    public void sendThePostRequestForBookAssign() {
        response = given(spec).contentType(ContentType.JSON).body(expectedDataBook).post("{first}/{second}/{third}");
        response.prettyPrint();
    }

    @Then("do assertion for book assign")
    public void doAssertionForBookAssign() {
        assertEquals(201, response.statusCode());
        assertFalse(response.jsonPath().getList("books").isEmpty());
    }

    @Given("set the url for account info")
    public void setTheUrlForAccountInfo() {
        //https://bookstore.demoqa.com/Account/v1/User/{{userId}}
        spec.pathParams("first", "Account", "second", "v1", "third", "User", "forth", userID);
    }

    @When("send the post request for account info")
    public void sendThePostRequestForAccountInfo() {
        response = given(spec).get("{first}/{second}/{third}/{forth}");
        response.prettyPrint();
    }

    @Then("do assertion for account info")
    public void doAssertionForAccountInfo() {
        assertEquals(200, response.statusCode());
        assertEquals(username, response.jsonPath().getString("username"));
        assertEquals(userID, response.jsonPath().getString("userId"));
        assertFalse(response.jsonPath().getList("books").isEmpty());
    }
}
