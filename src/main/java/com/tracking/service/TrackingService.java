package com.tracking.service;

import org.springframework.stereotype.Service;
import com.tracking.model.TrackingResponse;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TrackingService {

    private final AtomicLong counter = new AtomicLong();

    public TrackingResponse generateTrackingNumber(String origin, String destination, double weight,
                                                   OffsetDateTime createdAt, UUID customerId,
                                                   String customerName, String customerSlug) {

        String base = origin.toUpperCase() + destination.toUpperCase() +
                      customerSlug.toUpperCase().replace("-", "").substring(0, 3);
        String unique = Long.toHexString(counter.incrementAndGet()).toUpperCase();
        String trackingNumber = (base + unique).substring(0, Math.min((base + unique).length(), 16));

        return new TrackingResponse(trackingNumber, createdAt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
