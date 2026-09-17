package com.cravedash.restaurant.service;

import com.cravedash.restaurant.entity.Restaurant;
import com.cravedash.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository=restaurantRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        if(restaurant.getActive()==null){
            restaurant.setActive(true);
        }
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id){
        return restaurantRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Restaurant not found with id: "+id));
    }
    public Restaurant updateRestaurant(Long id, Restaurant updateRestaurant){
        Restaurant restaurant  = getRestaurantById(id);
        restaurant.setName(updateRestaurant.getName());
        restaurant.setAddress(updateRestaurant.getAddress());
        restaurant.setPhone(updateRestaurant.getPhone());

        return restaurantRepository.save(restaurant);
    }
    public void deleteRestaurant(Long id){
        Restaurant restaurant = getRestaurantById(id);
        restaurantRepository.delete(restaurant);
    }


}
