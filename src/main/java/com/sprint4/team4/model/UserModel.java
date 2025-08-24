package com.sprint4.team4.model;
import com.sprint4.team4.enums.Enums.Preference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	@Column(nullable = false , unique = true)
	private String userId;
	
	@Size(max = 50, message = "Name cannot exceed 50 characters")
	private String name;
	
	@Email(message = "Email should be valid")
	private String email;
	
	@Size(min = 10, max = 10, message="Mobile number must be 10 digits")
	private String mobileNumber;
	
	@Size(min=1, max=6, message="Country code must be between 1 and 6 charactors")
	private String countryCode;
	
	private String address;
	
	@Size(max = 30, message = "Password cannot exceed 30 characters")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Preference preference;
	
	@Transient 
	private String confirmPassword;

}
