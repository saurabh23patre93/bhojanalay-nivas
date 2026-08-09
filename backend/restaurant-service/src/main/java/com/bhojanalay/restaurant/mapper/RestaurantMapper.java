package com.bhojanalay.restaurant.mapper;

import com.bhojanalay.restaurant.dto.request.CreateRestaurantRequest;
import com.bhojanalay.restaurant.dto.response.RestaurantResponse;
import com.bhojanalay.restaurant.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Restaurant toEntity(CreateRestaurantRequest request);

    @Mapping(source = "id", target = "restaurantId")
    RestaurantResponse toResponse(Restaurant restaurant);
}