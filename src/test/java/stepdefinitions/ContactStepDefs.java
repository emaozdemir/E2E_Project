package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.ContactPojo;
import utilities.ObjectMapperUtils;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.patch;

public class ContactStepDefs {

    ContactPojo payload;//class seviyesinde tanımlarız clasta istedigimiz yerde kullanmak icin
    Response response;

    @Given("set the url for adding contact")
    public void setTheUrlForAddingContact() {
        spec.pathParam("first","contacts");
    }

    @And("set the expected data for adding contact")
    public void setTheExpectedDataForAddingContact() {
        String payloadStr = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";
        payload = ObjectMapperUtils.convertJsonStrToJava(payloadStr, ContactPojo.class);

    }

    @When("send the post request for adding contact")
    public void sendThePostRequestForAddingContact() {
        response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();
    }

    @Then("do assertion for adding contact")
    public void doAssertionForAddingContact() {
        ContactPojo actualData = ObjectMapperUtils.convertJsonStrToJava(response.asString(), ContactPojo.class);

        Assert.assertEquals(201,response.statusCode());
        Assert.assertEquals(payload.getFirstName(),actualData.getFirstName());
        Assert.assertEquals(payload.getLastName(),actualData.getLastName());
        Assert.assertEquals(payload.getCountry(),actualData.getCountry());
        Assert.assertEquals(payload.getBirthdate(),actualData.getBirthdate());
        Assert.assertEquals(payload.getPhone(),actualData.getPhone());
        // Gerisini tamamlayınız...

    }
}