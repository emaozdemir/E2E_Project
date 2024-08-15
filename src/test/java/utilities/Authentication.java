package utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken(){
//        String credentials= """
//                {
//                    "email": "test2@fake.com",
//                    "password": "myNewPassword"
//                }  """; bunun yerine map yaptık

        Map<String,String> credential = new HashMap<>();

        credential.put("email",ConfigReader.getProperty("contact_list_username"));
        credential.put("password",ConfigReader.getProperty("contact_list_password"));

        Response response =given().
                body(credential).
                contentType(ContentType.JSON).
                post("https://thinking-tester-contact-list.herokuapp.com/users/login");
        return response.jsonPath().getString("token");
    }


}
