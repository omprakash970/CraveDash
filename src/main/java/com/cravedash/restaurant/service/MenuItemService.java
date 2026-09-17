package com.cravedash.restaurant.service;

import com.cravedash.restaurant.entity.MenuItem;
import com.cravedash.restaurant.entity.Restaurant;
import com.cravedash.restaurant.repository.MenuItemRepository;
import com.cravedash.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(
            MenuItemRepository menuItemRepository,
            RestaurantRepository restaurantRepository) {

        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // Add menu item to a restaurant
    public MenuItem addMenuItem(Long restaurantId, MenuItem menuItem) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() ->
                        new RuntimeException("Restaurant not found"));

        menuItem.setRestaurant(restaurant);

        if (menuItem.getAvailable() == null) {
            menuItem.setAvailable(true);
        }

        return menuItemRepository.save(menuItem);
    }

    // Get all menu items of a restaurant
    public List<MenuItem> getRestaurantMenu(Long restaurantId) {

        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RuntimeException("Restaurant not found");
        }

        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    // Get one menu item
    public MenuItem getMenuItem(Long menuItemId) {

        return menuItemRepository.findById(menuItemId)
                .orElseThrow(() ->
                        new RuntimeException("Menu item not found"));
    }

    // Update menu item
    public MenuItem updateMenuItem(
            Long menuItemId,
            MenuItem updatedItem) {

        MenuItem menuItem = getMenuItem(menuItemId);

        menuItem.setName(updatedItem.getName());
        menuItem.setDescription(updatedItem.getDescription());
        menuItem.setPrice(updatedItem.getPrice());
        menuItem.setAvailable(updatedItem.getAvailable());

        return menuItemRepository.save(menuItem);
    }

    // Delete menu item
    public void deleteMenuItem(Long menuItemId) {

        MenuItem menuItem = getMenuItem(menuItemId);

        menuItemRepository.delete(menuItem);
    }
}