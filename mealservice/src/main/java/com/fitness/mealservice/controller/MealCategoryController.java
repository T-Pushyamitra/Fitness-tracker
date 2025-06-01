package com.fitness.mealservice.controller;


import com.fitness.mealservice.dto.request.MealCategoryRequestDto;
import com.fitness.mealservice.dto.response.MealCategoryResponseDto;
import com.fitness.mealservice.mappers.ApiResponse;
import com.fitness.mealservice.model.MealCategory;
import com.fitness.mealservice.service.MealCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal_category")
@RequiredArgsConstructor
public class MealCategoryController {
    private final MealCategoryService mealCategoryService;

    @PostMapping
    public ApiResponse create(@RequestBody List<MealCategoryRequestDto> mealCategoryList) {
        try {
            List<MealCategoryResponseDto> saved = mealCategoryService.saveAll(mealCategoryList);
            return ApiResponse.builder()
                    .message("Saved Meal Category")
                    .data(saved)
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (IllegalArgumentException e) {
            return ApiResponse.builder()
                    .message(e.getMessage())
                    .data(null)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @GetMapping
    public ApiResponse list(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = (direction.equalsIgnoreCase("desc")) ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        List<MealCategoryResponseDto> mealCategoryList = mealCategoryService.list(sort);

        if (mealCategoryList == null) {
            return ApiResponse.builder()
                    .message("Not Found")
                    .data(null)
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
        }
        return ApiResponse.builder()
                .message("Found Meal Category")
                .data(mealCategoryList)
                .status(HttpStatus.OK.value())
                .build();
    }
}
