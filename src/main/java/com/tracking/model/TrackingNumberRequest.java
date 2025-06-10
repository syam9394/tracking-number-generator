package com.tracking.model;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TrackingNumberRequest {
	
	 	@NotBlank
	    private String originCountryId;

	    @NotBlank
	    private String destinationCountryId;

	    @NotNull
	    private Double weight;

	    @NotNull
	    private UUID customerId;

	    @NotBlank
	    private String customerName;

	    @NotBlank
	    private String customerSlug;

	    // Getters and Setters
	    public String getOriginCountryId() { return originCountryId; }
	    public void setOriginCountryId(String originCountryId) { this.originCountryId = originCountryId; }

	    public String getDestinationCountryId() { return destinationCountryId; }
	    public void setDestinationCountryId(String destinationCountryId) { this.destinationCountryId = destinationCountryId; }

	    public Double getWeight() { return weight; }
	    public void setWeight(Double weight) { this.weight = weight; }

	    public UUID getCustomerId() { return customerId; }
	    public void setCustomerId(UUID customerId) { this.customerId = customerId; }

	    public String getCustomerName() { return customerName; }
	    public void setCustomerName(String customerName) { this.customerName = customerName; }

	    public String getCustomerSlug() { return customerSlug; }
	    public void setCustomerSlug(String customerSlug) { this.customerSlug = customerSlug; }

}
