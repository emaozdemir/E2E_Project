package utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static stepdefinitions.BookStoreStepDefs.username;
import static stepdefinitions.EndToEndStepDefs.email;


public class Authentication {

    public static String generateToken() {
//        String credentials= """
//                {
//                    "email": "test2@fake.com",
//                    "password": "myNewPassword"
//                }  """; bunun yerine map yaptık


        Map<String, String> credential = new HashMap<>();
        if (email == null) {//kullanıcı ya da yonetici gibi girmek icin ayrı maillerle is yapabiliriz
            credential.put("email", ConfigReader.getProperty("contact_list_username"));
            credential.put("password", ConfigReader.getProperty("contact_list_password"));
        } else {
            credential.put("email", email);
            credential.put("password", "Tester.14");
        }
        Response response = given().
                body(credential).
                contentType(ContentType.JSON).
                post("https://thinking-tester-contact-list.herokuapp.com/users/login");
        return response.jsonPath().getString("token");
    }

    public static String generateTokenBS(){

        pojos.BookStorePojos.UsernamePassword usernamePassword = new pojos.BookStorePojos.UsernamePassword();
        if (username==null){
            usernamePassword.setUserName("Ona63");
            usernamePassword.setPassword("Clarusway@2024");
        }else {
            usernamePassword.setUserName(username);
            usernamePassword.setPassword("Clarusway@2024");
        }

        Response response = RestAssured.given().contentType(ContentType.JSON).body(usernamePassword).post("https://bookstore.demoqa.com/Account/v1/GenerateToken");

        return response.jsonPath().getString("token");

    }

    public static void main(String[] args) {
        generateTokenBS();
    }


}