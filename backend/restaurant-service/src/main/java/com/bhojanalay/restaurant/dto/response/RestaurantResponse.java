package com.bhojanalay.restaurant.dto.response;


import com.bhojanalay.restaurant.enums.RestaurantStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponse {

    private UUID restaurantId;

    private String restaurantName;

    private String ownerName;

    private String email;

    private String mobile;

    private String gstNumber;

    private String fssaiNumber;

    private String description;

    private BigDecimal rating;

    private RestaurantStatus status;

    private Boolean verified;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}