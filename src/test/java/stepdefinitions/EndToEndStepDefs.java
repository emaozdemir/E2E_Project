package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactListAddUserPage;
import pages.ContactListHomePage;
import utilities.ConfigReader;
import utilities.Driver;

import java.security.PublicKey;

public class EndToEndStepDefs {
    ContactListHomePage homePage;
    ContactListAddUserPage addUserPage;
    public static String email;

    @Given("user goes to {string}")
    public void userGoesTo(String url) {
        Driver.getDriver().get(url);
    }

    @When("user clicks on sign up button")
    public void userClicksOnSignUpButton() {
        homePage = new ContactListHomePage();
        homePage.signup.click();

    }

    @And("User enters firstname, lastname, email, password")
    public void userEntersFirstnameLastnameEmailPassword() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        String password = "Tester.14";

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
    }
}
