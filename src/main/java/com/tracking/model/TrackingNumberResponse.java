package com.tracking.model;

import java.time.OffsetDateTime;

public class TrackingNumberResponse {

		private String trackingNumber;
	    private OffsetDateTime createdAt;

	    public TrackingNumberResponse(String trackingNumber, OffsetDateTime createdAt) {
	        this.trackingNumber = trackingNumber;
	        this.createdAt = createdAt;
	    }

	    public String getTrackingNumber() {
	        return trackingNumber;
	    }

	    public OffsetDateTime getCreatedAt() {
	        return createdAt;
	    }
}
