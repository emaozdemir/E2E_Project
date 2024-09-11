package stepdefinitions;

import io.cucumber.java.Before;//DİKKAT

import static base_urls.BookStoreBaseUrl.setSpec;


public class Hooks {

    @Before//Her scenario öncesi çalışır
    public void before(){
        setSpec();
    }

}
