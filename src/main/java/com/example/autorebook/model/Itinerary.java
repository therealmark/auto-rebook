package com.example.autorebook.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 *
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private Traveler passenger;
    private String passengerNameRecord;
    private List<Booking> bookings = new ArrayList<>();

    public Itinerary setPassenger(Traveler passenger) {
        this.passenger = passenger;

        return this;
    }

    public Itinerary addBooking(Booking booking) {
        this.bookings.add(booking);
        return  this;
    }
}
