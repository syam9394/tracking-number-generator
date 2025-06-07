package com.tracking.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import com.tracking.service.TrackingService;
import com.tracking.model.TrackingResponse;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/next-tracking-number")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping
    public TrackingResponse generateTrackingNumber(
            @RequestParam("origin_country_id") String originCountryId,
            @RequestParam("destination_country_id") String destinationCountryId,
            @RequestParam("weight") double weight,
            @RequestParam("created_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime createdAt,
            @RequestParam("customer_id") UUID customerId,
            @RequestParam("customer_name") String customerName,
            @RequestParam("customer_slug") String customerSlug) {
        return trackingService.generateTrackingNumber(originCountryId, destinationCountryId, weight, createdAt,
                customerId, customerName, customerSlug);
    }
}
