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

    private static Response response;

    @Given("I am an authorized user")
    public void iAmAnAuthorizedUser() throws JsonProcessingException {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body((new ObjectMapper()).writeValueAsString(Author.builder().name("n1").build())).post();

        response = request.get();

        String jsonString = response.asString();
        System.out.println(jsonString);

    }

    @Given("list of authors available")
    public void listOfAuthorsAreAvailable() {

    }

    @When("I add an author")
    public void addAuthor() {

    }

    @Then("Author is added")
    public void authorIsAdded() {

    }

    @When("I updated Author")
    public void updatedAuthor() {

    }

    @Then("Author is updated")
    public void authorIsUpdated() {

    }

    @When("I delete Author")
    public void deleteAuthor() {

    }

    @Then("Author is deleted")
    public void authorIsDeleted() {

    }

}
