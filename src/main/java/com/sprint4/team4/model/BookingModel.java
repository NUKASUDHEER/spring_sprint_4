package com.sprint4.team4.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sprint4.team4.enums.Enums.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name="bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	// Sender details
	@Size(min=2,max=50, message="Sender name must be between 2 and 50 characters")
	private String senderName;
	
	@Size(max=200, message="Sender address cannot exceed 200 characters")
	private String senderAddress;
	
	@Size(min=10,max=10,message="Sender mobile number must be 10 digits")
	private	String senderMobileNumber;
	
	// Receiver details
	@Size(min=2,max=50, message="Receiver name must be between 2 and 50 characters")
	private String recieverName;
	
	@Size(max=200, message="Receiver address cannot exceed 200 characters")
	private String recieverAddress;
	
	@Size(min=6,max=6,message="Receiver pin code must be 6 digits")
	private String recieverPinCode;
	
	@Size(min=10,max=10,message="Receiver mobile number must be 10 digits")
	private String recieverMobileNumber;
	
	// Parcel details
	@Enumerated(EnumType.STRING)
	private ParcelSize size;
	
	@Positive(message="Weight must be greater than 0")
	private double weight;
	
	@Size(max=300,message="Description cannot exceed 300 characters")
	private String description; 
	
	// Shipping details
	@Enumerated(EnumType.STRING)
	private DeliverySpeed deliverySpeed;
	
	@Enumerated(EnumType.STRING)
	private PackagindPreference packagingPreference;
	
	// Payment details
	@Positive(message="Cost must be greater than 0")
	private double cost;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod method;
	
	// Additional services
	private boolean insurance = false;
	private boolean tracking = false;

	private String userId;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime dropDateTime;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime pickupDateTime;
	
	private BookingStatus status = BookingStatus.PENDING;
	
}
