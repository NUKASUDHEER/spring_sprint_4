package com.sprint4.team4.enums;

public class Enums {
	public enum Preference{
		MAIL_DELIVERY,
		NOTIFICATIONS
	}
	
	public enum ParcelSize{
		SMALL,
		MEDIUM,
		LARGE
	}
	
	public enum DeliverySpeed{
		STANDARD,
		EXPRESS
	}
	
	public enum PackagindPreference{
		STANDARD,
		CUSTOM,
		ECO_FRIENDLY,
		FRAGILE
	}
	
	public enum PaymentMethod{
		CARD,
		UPI,
		NET_BANKING
	}
	
	public enum BookingStatus{
		PENDING,
	    PICKUP,
	    IN_TRANSIT,
	    DELIVERED,
	    RETURNED
	}
	
}
