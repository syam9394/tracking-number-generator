package com.tracking.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.tracking.service.TrackingNumberService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

import com.tracking.model.TrackingNumberRequest;
import com.tracking.model.TrackingNumberResponse;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TrackingController {

	@Autowired
	private TrackingNumberService trackingNumberService;
	
	
    @Operation(summary = "API to generate a unique tracking number")
    @PostMapping(path = "/next-tracking-number", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getNextTrackingNumber(
            @Valid @RequestBody TrackingNumberRequest request) {

        HttpStatus responseStatus;
        TrackingNumberResponse response;
        Map<String, Object> responseBody = new LinkedHashMap<>();

        try {
            String trackingNumber = trackingNumberService.generate(request);
            response = new TrackingNumberResponse(trackingNumber, OffsetDateTime.now());
            responseBody.put("status", "success");
            responseBody.put("data", response);
            responseStatus = HttpStatus.OK;
        } catch (ConstraintViolationException e) {
        	  List<String> errors = e.getConstraintViolations().stream()
                      .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                      .collect(Collectors.toList());

            responseBody.put("status", "failure");
            responseBody.put("errors", errors);
            responseStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception ex) {
            responseBody.put("status", "failure");
            responseBody.put("errors", List.of("Unexpected error: " + ex.getMessage()));
            responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        responseBody.put("timestamp", OffsetDateTime.now());
        responseBody.put("statusCode", responseStatus.value());
        return new ResponseEntity<>(responseBody, responseStatus);
    }
}
