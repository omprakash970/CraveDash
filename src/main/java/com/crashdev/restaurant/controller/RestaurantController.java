package com.crashdev.restaurant.controller;


import com.crashdev.restaurant.entity.Restaurant;
import com.crashdev.restaurant.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        return ResponseEntity.status(HttpsURLConnection.HTTP_CREATED).body(restaurantService.createRestaurant(restaurant));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
       return ResponseEntity.ok(
               restaurantService.getAllRestaurants()
       );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id){
        return ResponseEntity.ok(
                restaurantService.getRestaurantById(id)
        );
    }




}
