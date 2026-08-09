package com.bhojanalay.restaurant.repository;

import com.bhojanalay.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    Optional<Restaurant> findByEmail(String email);
    Optional<Restaurant> findByMobile(String mobile);
    boolean existsByEmail(String email);
    boolean existsByMobile(String mobile);
    boolean existsByGstNumber(String gstNumber);
    boolean existsByFssaiNumber(String fssaiNumber);
}