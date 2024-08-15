package utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {
    public static void main(String[] args) {
        System.out.println("generateToken() = " + generateToken());
        System.out.println("sema");
    }

    public static String generateToken(){
        Map<String,String> credential = new HashMap<>();
        credential.put("email",ConfigReader.getProperty("contact_list_username"));
        //credential.put("email","tester@test12.com");
        credential.put("password",ConfigReader.getProperty("contact_list_password"));

        Response response =given().
                body(credential).
                contentType(ContentType.JSON).
                post(ConfigReader.getProperty("contact_list_url"));
        return response.jsonPath().getString("token");
    }


}
