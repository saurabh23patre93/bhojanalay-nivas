package com.bhojanalay.restaurant.service.impl;

import com.bhojanalay.restaurant.dto.request.CreateRestaurantRequest;
import com.bhojanalay.restaurant.dto.response.RestaurantResponse;
import com.bhojanalay.restaurant.entity.Restaurant;
import com.bhojanalay.restaurant.exception.DuplicateResourceException;
import com.bhojanalay.restaurant.mapper.RestaurantMapper;
import com.bhojanalay.restaurant.repository.RestaurantRepository;
import com.bhojanalay.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Override
    public RestaurantResponse createRestaurant(CreateRestaurantRequest request) {
        validateDuplicateEmail(request.getEmail());
        validateDuplicateMobile(request.getMobile());
        validateDuplicateGstNumber(request.getGstNumber());
        validateDuplicateFssaiNumber(request.getFssaiNumber());
        Restaurant restaurant = restaurantMapper.toEntity(request);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponse(savedRestaurant);
    }

    private void validateDuplicateEmail(String email) {
        if (restaurantRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("Restaurant with email already exists");
        }
    }

    private void validateDuplicateMobile(String mobile) {
        if (restaurantRepository.existsByMobile(mobile)) {
            throw new DuplicateResourceException("Restaurant with mobile number already exists");
        }
    }

    private void validateDuplicateGstNumber(String gstNumber) {
        if (gstNumber != null && !gstNumber.isBlank() && restaurantRepository.existsByGstNumber(gstNumber)) {
            throw new DuplicateResourceException("Restaurant with GST number already exists");
        }
    }

    private void validateDuplicateFssaiNumber(String fssaiNumber) {
        if (fssaiNumber != null && !fssaiNumber.isBlank() && restaurantRepository.existsByFssaiNumber(fssaiNumber)) {
            throw new DuplicateResourceException("Restaurant with FSSAI number already exists");
        }
    }
}