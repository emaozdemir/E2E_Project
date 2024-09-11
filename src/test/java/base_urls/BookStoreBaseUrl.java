package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


import static utilities.Authentication.generateTokenBS;//DİKKAT

public class BookStoreBaseUrl {

    public static RequestSpecification spec;


    public static void setSpec(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://bookstore.demoqa.com/")
                .addHeader("Authorization", "Bearer "+generateTokenBS())
                .build();
    }

}
