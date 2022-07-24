package com.example.autorebook.model;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 **/
@Data
@AllArgsConstructor
public class Traveler {
    private String firstName;
    private String lastName;

    public Traveler() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
    }
}
