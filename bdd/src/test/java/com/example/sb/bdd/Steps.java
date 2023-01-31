package com.example.sb.bdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {

    private static final String BASE_URL = "http://localhost:8080/api/author";

    private static RequestSpecification request;
    private static Response response;

    @Given("create author request prepared")
    public void createAuthorRequestPrepared() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();

    }

    @When("I place request for create author")
    public void iPlacedRequestForCreateAuthor() throws JsonProcessingException {
        request.header("Content-Type", "application/json");
        response = request.body((new ObjectMapper()).writeValueAsString(Author.builder().name("n1").build())).post();
        response = request.get();
    }

    @Then("author is created")
    public void authorIsAdded() {
        String jsonString = response.asString();
    }

}
