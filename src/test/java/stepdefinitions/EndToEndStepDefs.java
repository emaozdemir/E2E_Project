package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.ContactListAddUserPage;
import pages.ContactListHomePage;
import pojos.ContactUserPojo;
import pojos.UpdateUserPojo;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ObjectMapperUtils;

import java.security.PublicKey;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;

public class EndToEndStepDefs {
    ContactListHomePage homePage;
    ContactListAddUserPage addUserPage;
    public static String email;
    String firstName;
    String lastName;
    String password;
    UpdateUserPojo payload;
    Response response;

    @Given("user goes to {string}")
    public void userGoesTo(String url) {
        Driver.getDriver().get(url);
    }

    @When("user clicks on sign up button")
    public void userClicksOnSignUpButton() {
        homePage = new ContactListHomePage();
        homePage.signup.click();

    }
    Faker faker;
    @And("User enters firstname, lastname, email, password")
    public void userEntersFirstnameLastnameEmailPassword() {
        faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        password = "Tester.14";

        addUserPage = new ContactListAddUserPage();
        addUserPage.firstName.sendKeys(firstName);
        addUserPage.lastName.sendKeys(lastName);
        addUserPage.email.sendKeys(email);
        addUserPage.password.sendKeys(password);

    }

    @And("user clicks on submit button")
    public void userClicksOnSubmitButton() {
        addUserPage.submit.click();
    }

    @And("user closes browser")
    public void userClosesBrowser() {
        Driver.closeDriver();
    }

    @Then("verify the user via API request")
    public void verifyTheUserViaAPIRequest() {
        spec.pathParams("first","users"
                ,"second","me");

        ContactUserPojo expectedData = new ContactUserPojo(firstName,lastName,null,null,email);
        System.out.println("expectedData = " + expectedData);
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        ContactUserPojo actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), ContactUserPojo.class);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        Assert.assertEquals(expectedData.getLastName(),actualData.getLastName());
        Assert.assertEquals(expectedData.getEmail(),actualData.getEmail());
    }

    @Given("set the url for update")
    public void setTheUrlForUpdate() {
        spec.pathParams("first","users"
                ,"second","me");

    }
    @And("set the expected data for update")
    public void setTheExpectedDataForUpdate() {
        System.out.println("email = " + email);
        faker = new Faker();
        email= faker.internet().emailAddress();
        System.out.println("email = " + email);
        payload = new UpdateUserPojo("Tarıq","Any","Tester.14",email);
    }

    @When("send the patch request for update")
    public void sendThePatchRequestForUpdate() {
        response = given(spec).body(payload).when().patch("{first}/{second}");
        response.prettyPrint();
    }

    @Then("do assertion for update")
    public void doAssertionForUpdate() {
        ContactUserPojo actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), ContactUserPojo.class);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(payload.getFirstName(),actualData.getFirstName());
        Assert.assertEquals(payload.getLastName(),actualData.getLastName());
        Assert.assertEquals(payload.getEmail(),actualData.getEmail());
    }

    @Given("set the url for delete")
    public void setTheUrlForDelete() {
        spec.pathParams("first","users"
                ,"second","me");
    }
    Response responseDelete;
    @When("send the delete request")
    public void sendTheDeleteRequest() {
        responseDelete = given(spec).when().delete("{first}/{second}");
    }

    @Then("do assertion for delete")
    public void doAssertionForDelete() {
        String deleteResponse = responseDelete.asString();
        Assert.assertTrue(deleteResponse.isEmpty());
    }
}
