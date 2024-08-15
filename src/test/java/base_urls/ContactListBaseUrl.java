package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static utilities.Authentication.generateToken;

public class ContactListBaseUrl {

    public static RequestSpecification spec;


    static {//static kod blogu -> bu clastan herhangi bir is yaparsak ilk calısacak code static bloktur.
        spec = new RequestSpecBuilder()
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .addHeader("Authorization","Bearer "+ generateToken())
                .setContentType(ContentType.JSON)
                .build();
    }

}