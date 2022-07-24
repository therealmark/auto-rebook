package com.example.autorebook;

import com.example.autorebook.model.Booking;
import com.example.autorebook.model.Itinerary;
import com.example.autorebook.model.Traveler;
import com.example.autorebook.service.ItineraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class AutoRebookApplication implements CommandLineRunner {

    @Autowired
    ItineraryService itineraryService;
    Logger LOG = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    public static void main(String[] args) {
        SpringApplication.run(AutoRebookApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int count = 0;
        while (count < 1000) {
            generateItinerary();
            count++;
        }

    }

    private void generateItinerary() throws InterruptedException {
        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();
        Date fiveYearsAgo = new Date(now - aDay * 365 * 5);
        Date oneDayAgo = new Date(now - aDay * 1);
        Date random = between(fiveYearsAgo, oneDayAgo);
        Itinerary itinerary = new Itinerary();
        itinerary.setPassenger(new Traveler());
        itinerary.setPassengerNameRecord(randomPnr());
        for (int i = 0; i < randomInt(); i++) {
            Booking booking = new Booking().setBookedOn(random)
                    .setPrice(randomPrice())
                    .setDepartingAirport("SEA")
                    .setArrivingAirport("LAX");
            itinerary.addBooking(booking);
            random = new Date(random.getTime() + 3600 * 1000); // move date forward 1hr
            TimeUnit.SECONDS.sleep(2);
        }

        Itinerary persistedItinerary = itineraryService.createItinerary(itinerary);

        LOG.info(persistedItinerary.toString());
    }

    private String randomPnr() {
        return UUID.randomUUID()
                .toString()
                .substring(0, 10).toUpperCase().replace("-","");
    }


    public static Date between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

    Integer randomPrice() {
        Random r = new Random();
        int low = 400;
        int high = 2000;
        return r.nextInt(high-low) + low;
    }

    int randomInt() {
        Random r = new Random();
        int low = 1;
        int high = 8;
        return r.nextInt(high-low) + low;
    }
}
