package com.bhojanalay.restaurant.service;

import com.bhojanalay.restaurant.dto.request.CreateRestaurantRequest;
import com.bhojanalay.restaurant.dto.response.RestaurantResponse;

public interface RestaurantService {
    RestaurantResponse createRestaurant(CreateRestaurantRequest request);
}