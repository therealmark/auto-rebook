package com.example.autorebook.service;

import com.example.autorebook.client.DBClient;
import com.example.autorebook.model.Itinerary;
import com.example.autorebook.model.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 *
 **/
@Service
@DependsOn(value = "DBClient")
public class UserService {

    private DBClient dbClient;

    @Autowired
    public UserService(DBClient dbClient) {
        this.dbClient = dbClient;
    }

    public Itinerary createItinerary(Itinerary itinerary) {
        return dbClient.insertItinerary(itinerary);
    }

    public Itinerary findItineraryById(String id) {
        return dbClient.findItineraryById(id);
    }
}
