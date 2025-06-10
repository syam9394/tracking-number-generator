package com.tracking.service;

import java.util.UUID;

import com.tracking.model.TrackingNumberRequest;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class TrackingNumberService {

	 private static final Logger logger = LoggerFactory.getLogger(TrackingNumberService.class);

	    private final Counter trackingNumberGeneratedCounter;

	    public TrackingNumberService(MeterRegistry meterRegistry) {
	        this.trackingNumberGeneratedCounter = meterRegistry.counter("tracking.number.generated.count");
	    }

	    public String generate(TrackingNumberRequest request) {
	        logger.info("Starting tracking number generation for customer: {}", request.getCustomerSlug());

	        try {
	            String trackingNumber = generateTrackingNumber(
	                    request.getOriginCountryId(),
	                    request.getDestinationCountryId(),
	                    request.getCustomerSlug()
	            );

	            trackingNumberGeneratedCounter.increment();
	            logger.debug("Generated tracking number: {}", trackingNumber);
	            return trackingNumber;

	        } catch (Exception e) {
	            logger.error("Error while generating tracking number", e);
	            throw new RuntimeException("Failed to generate tracking number", e);
	        }
	    }

	    private String generateTrackingNumber(String origin, String destination, String slug) {
	        String customerCode = slug.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
	        customerCode = customerCode.length() > 3
	                ? customerCode.substring(0, 3)
	                : String.format("%-3s", customerCode).replace(' ', 'X');

	        String randomPart = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9).toUpperCase();

	        String trackingNumber = origin.toUpperCase() + destination.toUpperCase() + customerCode + randomPart;

	        logger.info("Constructed tracking number: {}", trackingNumber);

	        return trackingNumber;
	    }
}
