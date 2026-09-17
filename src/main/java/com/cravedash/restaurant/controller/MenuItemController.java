package com.cravedash.restaurant.controller;

import com.cravedash.restaurant.entity.MenuItem;
import com.cravedash.restaurant.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    // Add menu item
    @PostMapping("/{restaurantId}/menu")
    public ResponseEntity<MenuItem> addMenuItem(
            @PathVariable Long restaurantId,
            @RequestBody MenuItem menuItem) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(menuItemService.addMenuItem(
                        restaurantId,
                        menuItem
                ));
    }

    // Get restaurant menu
    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<List<MenuItem>> getRestaurantMenu(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                menuItemService.getRestaurantMenu(restaurantId)
        );
    }

    // Get menu item
    @GetMapping("/menu/{menuItemId}")
    public ResponseEntity<MenuItem> getMenuItem(
            @PathVariable Long menuItemId) {

        return ResponseEntity.ok(
                menuItemService.getMenuItem(menuItemId)
        );
    }

    // Update menu item
    @PutMapping("/menu/{menuItemId}")
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable Long menuItemId,
            @RequestBody MenuItem menuItem) {

        return ResponseEntity.ok(
                menuItemService.updateMenuItem(
                        menuItemId,
                        menuItem
                )
        );
    }

    // Delete menu item
    @DeleteMapping("/menu/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItem(
            @PathVariable Long menuItemId) {



        menuItemService.deleteMenuItem(menuItemId);

        return ResponseEntity.noContent().build();
    }
}