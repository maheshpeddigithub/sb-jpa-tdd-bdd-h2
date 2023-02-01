package com.example.sb.bdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Steps {

    private static final String BASE_URL = "http://localhost:8080/api/author";

    private static Response response;
    private static Author author = null;

    @Given("create author request received")
    public void createAuthorRequestReceived() {
    }

    @When("I place request for create author")
    public void iPlaceRequestForCreateAuthor() throws JsonProcessingException {
        response = given().header("Content-Type", "application/json").body((new ObjectMapper()).writeValueAsString(Author.builder().name("n1").build())).post(BASE_URL);
    }

    @Then("author is created")
    public void authorIsCreated() {
        assertEquals(201, response.statusCode());
    }


    @Given("get author details request received")
    public void getAuthorDetailsRequestReceived() {
    }

    @When("I place request for get author details")
    public void iPlaceRequestForGetAuthorDetails() {
        response = given().header("Content-Type", "application/json").get(BASE_URL);
    }

    @Then("author details are retrieved")
    public void authorDetailsAreRetrieved() throws JsonProcessingException {
        assertEquals(200, response.statusCode());
        List<Author> authors = (new ObjectMapper()).readValue(response.getBody().asString(), new TypeReference<List<Author>>(){});
        author = authors.stream().findFirst().orElse(null);
        assertEquals("n1", author.getName());
    }

    @Given("update author details request received")
    public void updateAuthorDetailsRequestReceived() {
    }

    @When("I place request for update author details")
    public void iPlaceRequestForUpdateAuthorDetails() throws JsonProcessingException {
        response = given().header("Content-Type", "application/json").body((new ObjectMapper()).writeValueAsString(Author.builder().name("n11").build())).patch(BASE_URL + "/" + author.getId());
    }

    @Then("author details are updated")
    public void authorDetailsAreUpdated() throws JsonProcessingException {
        assertEquals(202, response.statusCode());
        response = given().header("Content-Type", "application/json").get(BASE_URL + "/" + author.getId());
        assertEquals(200, response.statusCode());
        Author author = (new ObjectMapper()).readValue(response.getBody().asString(), Author.class);
        assertEquals("n11", author.getName());
    }

    @Given("delete author request received")
    public void deleteAuthorDetailsRequestReceived() {
    }

    @When("I place request for delete author")
    public void iPlaceRequestForDeleteAuthor() {
        response = given().header("Content-Type", "application/json").delete(BASE_URL + "/" + author.getId());
    }

    @Then("author is deleted")
    public void authorIsDeleted() {
        assertEquals(202, response.statusCode());
    }

}
