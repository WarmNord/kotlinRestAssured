package io.swagger.petstore.controllers;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.swagger.petstore.models.JMessage;
import io.swagger.petstore.models.JPet;


public class JPetController {

    private RequestSpecification requestSpecification;

    public JPetController() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader("api_key", "1qa2ws3ed4rfvcxz")
                .setBaseUri("http://petstore.swagger.io")
                .setBasePath("/v2/pet")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();
    }

    public JPet addNewPet(JPet petRequest) {
        return given(requestSpecification)
                .body(petRequest)
                .post().as(JPet.class);
    }

    public void deletePet(JPet pet) {
        given(requestSpecification)
                .delete(pet.getId());
    }

    public JMessage getPet(JPet pet) {
        return given(requestSpecification)
                .get(pet.getId())
                .then()
                .extract().body().as(JMessage.class);
    }

}
