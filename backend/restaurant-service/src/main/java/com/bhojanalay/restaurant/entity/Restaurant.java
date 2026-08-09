package com.bhojanalay.restaurant.entity;

import com.bhojanalay.common.enums.RestaurantStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "restaurant_name", nullable = false, length = 200)
    private String restaurantName;

    @Column(name = "owner_name", nullable = false, length = 200)
    private String ownerName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 15)
    private String mobile;

    @Column(name = "gst_number", unique = true, length = 30)
    private String gstNumber;

    @Column(name = "fssai_number", unique = true, length = 50)
    private String fssaiNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 2, scale = 1)
    private BigDecimal rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RestaurantStatus status;

    @Column(nullable = false)
    private Boolean verified;

    @Column(nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        LocalDateTime now = LocalDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;

        if (this.status == null) {
            this.status = RestaurantStatus.PENDING_APPROVAL;
        }

        if (this.verified == null) {
            this.verified = false;
        }

        if (this.active == null) {
            this.active = true;
        }

        if (this.rating == null) {
            this.rating = BigDecimal.ZERO;
        }
    }

    @PreUpdate
    protected void onUpdate() {

        this.updatedAt = LocalDateTime.now();
    }
}