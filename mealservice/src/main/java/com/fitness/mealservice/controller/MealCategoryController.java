package com.fitness.mealservice.controller;


import com.fitness.mealservice.model.MealCategory;
import com.fitness.mealservice.service.MealCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal_category")
@RequiredArgsConstructor
public class MealCategoryController {
    private final MealCategoryService mealCategoryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<MealCategory> mealCategoryList) {
        try {
            List<MealCategory> _mealCategoryList = mealCategoryService.saveAll(mealCategoryList);
            return ResponseEntity.ok(_mealCategoryList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MealCategory>> list(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = (direction.equalsIgnoreCase("desc")) ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        List<MealCategory> mealCategoryList = mealCategoryService.list(sort);

        if (mealCategoryList == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(mealCategoryList);
    }
}
