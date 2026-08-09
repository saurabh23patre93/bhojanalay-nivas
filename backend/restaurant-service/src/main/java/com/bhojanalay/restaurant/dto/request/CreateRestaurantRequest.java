package com.bhojanalay.restaurant.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantRequest {

    @NotBlank(message = "Restaurant name is required")
    @Size(
            min = 3,
            max = 200,
            message = "Restaurant name must be between 3 and 200 characters"
    )
    private String restaurantName;

    @NotBlank(message = "Owner name is required")
    @Size(
            min = 3,
            max = 200,
            message = "Owner name must be between 3 and 200 characters"
    )
    private String ownerName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Please provide a valid Indian mobile number"
    )
    private String mobile;

    @Size(
            max = 30,
            message = "GST number cannot exceed 30 characters"
    )
    private String gstNumber;

    @Size(
            max = 50,
            message = "FSSAI number cannot exceed 50 characters"
    )
    private String fssaiNumber;

    @Size(
            max = 2000,
            message = "Description cannot exceed 2000 characters"
    )
    private String description;
}