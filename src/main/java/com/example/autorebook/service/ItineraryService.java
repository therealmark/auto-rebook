package com.example.autorebook.service;

import com.example.autorebook.client.DBClient;
import com.example.autorebook.model.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 *
 **/
@Service
@DependsOn(value = "DBClient")
public class ItineraryService {

    private DBClient dbClient;

    @Autowired
    public ItineraryService(DBClient dbClient) {
        this.dbClient = dbClient;
    }

    public Itinerary createItinerary(Itinerary itinerary) {
        return dbClient.insertItinerary(itinerary);
    }

    public Itinerary findItineraryById(String id) {
        return dbClient.findItineraryById(id);
    }
}
